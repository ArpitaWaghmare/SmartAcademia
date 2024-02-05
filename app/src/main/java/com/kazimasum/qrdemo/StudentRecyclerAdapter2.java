package com.kazimasum.qrdemo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentRecyclerAdapter2 extends RecyclerView.Adapter<StudentRecyclerAdapter2.ViewHolder>
{

    ArrayList<String> enrollments;
    Context context;

    public StudentRecyclerAdapter2(Context context, ArrayList<String> enrollments) {
        this.enrollments = enrollments;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentRecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custome_design,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRecyclerAdapter2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.textView.setText(enrollments.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked on " + enrollments.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return enrollments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textName);
        }
    }
}
