package com.project.yogaapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.yogaapp.Model.Exercise;
import com.project.yogaapp.R;
import com.project.yogaapp.Interface.ItemClickListener;
import com.project.yogaapp.ViewExercise;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ImageView image;
    public TextView text;
    public TextView desc;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.exerciseImage);
        text = itemView.findViewById(R.id.exerciseName);
        desc = itemView.findViewById(R.id.desc);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Exercise> exerciseList;
    private Context context;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText((exerciseList.get(position).getName()));
        holder.desc.setText((exerciseList.get(position).getDescription()));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, ViewExercise.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image_id", exerciseList.get(position).getImage_id());
                intent.putExtra("name", exerciseList.get(position).getName());
                intent.putExtra("desc", exerciseList.get(position).getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
