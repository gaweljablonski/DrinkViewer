package com.mojitoproject.drinkviewer.ui.favoriteList;

import static com.mojitoproject.drinkviewer.MainActivity.favoriteListOpensCounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.material.snackbar.Snackbar;
import com.mojitoproject.drinkviewer.ModelClass;
import com.mojitoproject.drinkviewer.MyAdapter;
import com.mojitoproject.drinkviewer.MyApi;
import com.mojitoproject.drinkviewer.R;
import com.mojitoproject.drinkviewer.databinding.ActivityMainBinding;
import com.mojitoproject.drinkviewer.databinding.FragmentFavoriteListBinding;
import com.mojitoproject.drinkviewer.databinding.FragmentViewDataBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteListFragment extends Fragment /*AppCompatActivity*/ {

    private FragmentFavoriteListBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentFavoriteListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
