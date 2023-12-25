package com.mojitoproject.drinkviewer.ui.insertData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mojitoproject.sidebar.ModelClass;
import com.mojitoproject.sidebar.MyApi;
import com.mojitoproject.sidebar.databinding.FragmentInsertDataBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertDataFragment extends Fragment{
    private FragmentInsertDataBinding binding;

    private EditText NameET, DescriptionET, IngredientsET, PercentageET;
    private Button add, viewdata;
    private MyApi myApi;
    private String BaseUrl = "https://mojitoproject.000webhostapp.com/connection_ftp/";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        InsertDataViewModel insertDataViewModel = new ViewModelProvider(this).get(InsertDataViewModel.class);

        binding = FragmentInsertDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        NameET = binding.Name;
        DescriptionET = binding.Description;
        IngredientsET = binding.Ingredients;
        PercentageET = binding.Ingredients;
        add = binding.add;
        viewdata = binding.view;


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        return root;
    }
    private void insertData() {
        int percentage = 0;
        String name = NameET.getText().toString();
        String description = DescriptionET.getText().toString();
        String ingredients = IngredientsET.getText().toString();
        try {
            percentage = Integer.parseInt(PercentageET.getText().toString());
        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
            Toast.makeText(getActivity(), "Data not correct", Toast.LENGTH_SHORT).show();
            return;
        }
        try {


            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            myApi = retrofit.create(MyApi.class);
            Call<ModelClass> modelClassCall = myApi.insertData(name, description, ingredients, percentage);

            modelClassCall.enqueue(new Callback<ModelClass>() {
                @Override
                public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                    Toast.makeText(getActivity(), "Data successfully inserted", Toast.LENGTH_SHORT).show();
                    NameET.setText("");
                    DescriptionET.setText("");
                    IngredientsET.setText("");
                    PercentageET.setText("");
                }

                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to inserted", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
//            throw new RuntimeException(e);
            Toast.makeText(getActivity(), "Data not correct", Toast.LENGTH_SHORT).show();
        }
    }
}
