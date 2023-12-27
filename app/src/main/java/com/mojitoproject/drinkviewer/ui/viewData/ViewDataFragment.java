package com.mojitoproject.drinkviewer.ui.viewData;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mojitoproject.drinkviewer.ModelClass;
import com.mojitoproject.drinkviewer.MyAdapter;
import com.mojitoproject.drinkviewer.MyApi;
import com.mojitoproject.drinkviewer.databinding.FragmentViewDataBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewDataFragment extends Fragment implements FiltersPop.OnSubmitListener{
    private FragmentViewDataBinding binding;

    private MyApi myApi;
    private MyAdapter myAdapter;
    final private String BaseUrl = "https://mojitoproject.000webhostapp.com/connection_ftp/";
    private RecyclerView recyclerView;
    private ArrayList<ModelClass> modelClasses;
    private String query = "";
    private Button show, filters;
    private EditText restET;
    private TextView elementCounter;

    ArrayList<String> alcoholsArary = new ArrayList<String>();
    ArrayList<String> ingrediencesArray = new ArrayList<String>();

    Context context;
    FiltersPop popup;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        SlideshowViewModel slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentViewDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        modelClasses = new ArrayList<>();
        recyclerView = binding.rv;
        show = binding.show;
        filters = binding.filters;
        restET = binding.restET;
        elementCounter = binding.elementCounter;
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayJson();
            }
        });

        // otwiera popup filtrów
        context = getActivity();
        popup = new FiltersPop(context, this);
        filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHideKeyboard();
                popup.setBackgroundDrawable(new ColorDrawable(
                        android.graphics.Color.TRANSPARENT));
                popup.show(v);
            }
        });

        displayJson();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // tworzy zapytanie z pola wyszukiwania i filtrów
    private void createQuery() {
        final String onlyWhere = "where ";
        final String nameFromET = deleteSpace(restET.getText().toString());
        query = "where ";

        // if'ujemy przypadek wyszukiwania spacji   &&   dobry OR między ()
        // część wyszukiwania: nazwa

        if(!nameFromET.equals("")){
            query = query + "Name like '%";
            query = query + nameFromET;
            query = query + "%'";

            if(alcoholsArary.size() > 0 || ingrediencesArray.size() > 0)
                query = query + " OR ";
        }

        // część wyszukiwania: alkochole
        if(alcoholsArary.size() > 0)
        {
            query = query + "(";
            for(int i = 0; i < alcoholsArary.size()-1; i++)
            {
                query = query + "Ingredients LIKE '%";
                query = query + alcoholsArary.get(i);
                query = query + "' OR ";
            }
            query = query + "Ingredients LIKE '%";
            query = query + alcoholsArary.get(alcoholsArary.size()-1);
            query = query + "')";
            if(ingrediencesArray.size() > 0)
                query = query +  " OR ";
        }


        // część wyszukiwania: reszta składników tj. owocki, cukier, jam etc.
        if(ingrediencesArray.size() > 0)
        {
            query = query + "(";
            for(int i = 0; i < ingrediencesArray.size()-1; i++)
            {
                query = query + "Ingredients LIKE '%";
                query = query + ingrediencesArray.get(i);
                query = query + "' AND ";
            }
            query = query + "Ingredients LIKE '%";
            query = query + ingrediencesArray.get(ingrediencesArray.size()-1);
            query = query + "')";
        }

        if(query.equals(onlyWhere))
            query = "";

    }

    // wyświetla zapytanie w MA2
    // pobrane z DB
    // z dodakiem do zapytania 'query'
    private void displayJson() {
        createQuery();
        // use retrofit for http request
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);

        //query -> dodatek do zapytania SQL (które można wykorzystać jako sortowanie, filtrowanie etc.)
//        query = "";

        Log.e("[DISPLAY JSON]", query);

        // TODO: zrozumieć następne 21 linijek  :)
        Call<ArrayList<ModelClass>> arrayListCall = myApi.fetchData(query);
        arrayListCall.enqueue(new Callback<ArrayList<ModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelClass>> call, Response<ArrayList<ModelClass>> response) {
                modelClasses = response.body();
                elementCounter.setText("" + modelClasses.size() + " results");
                // loop for recyclerview item form mysql
                for(int i = 0; i < modelClasses.size(); i++){
                    //set adapter
                    myAdapter = new MyAdapter(modelClasses, getActivity());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                    //now set layout
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(myAdapter);
                }
                if(modelClasses.size() == 0)
                {
                    //set adapter
                    myAdapter = new MyAdapter(modelClasses, getActivity());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                    //now set layout
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelClass>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
        myHideKeyboard();
    }

    // funkcja wywoływana po naciśnięciu SAVE w popup_window (czyli po zamknięciu)
    @Override
    public void valueChanged(ArrayList<String> alcoholsArary, ArrayList<String> ingrediencesArray) {
        this.alcoholsArary = alcoholsArary;
        this.ingrediencesArray = ingrediencesArray;
//        Log.e("ALKO", this.alcoholsArary.get(0));
        Log.e("PASSED THE ARRAYS FORM FILTERS", "SUCCES");

        displayJson();
    }

    // usuwa spacje wiodące z pola wyszukiwania
    private String deleteSpace(String s) {
        String outt = "";
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' '){
                continue;
            }
            for(int j = i; j < s.length(); j++){
                outt = outt + s.charAt(j);
            }
            break;
        }

        return outt;
    }
    private void myHideKeyboard() {
        // Check if no view has focus:
        // Hide Keyboard
        try {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }
}
