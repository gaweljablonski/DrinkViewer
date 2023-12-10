package com.mojitoproject.drinkviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ModelClass> arrayList;
    private Context context;
    private MyApi myApi;


    public MyAdapter(ArrayList<ModelClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        try {
            ModelClass modelClass = arrayList.get(position);
            holder.NameT.setText("nazwa:"+modelClass.getName());
            holder.DescriptionT.setText("opis:\n"+modelClass.getDescription());
            holder.IngredientsT.setText("skladniki:\n"+modelClass.getIngredients());
            holder.PercentageT.setText("%:"+modelClass.getPercentage());
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NameT, DescriptionT, IngredientsT, PercentageT;
        public ViewHolder(View view) {
            super(view);
            NameT = (TextView) view.findViewById(R.id.Name);
            DescriptionT = (TextView) view.findViewById(R.id.Description);
            IngredientsT = (TextView) view.findViewById(R.id.Ingredients);
            PercentageT = (TextView) view.findViewById(R.id.Percentage);
        }
    }
}
