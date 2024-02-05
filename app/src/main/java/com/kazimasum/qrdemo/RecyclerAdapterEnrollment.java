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

public class RecyclerAdapterEnrollment extends RecyclerView.Adapter<RecyclerAdapterEnrollment.ViewHolder>
{

        ArrayList<Student_info> enrollments;
        Context context;

        public RecyclerAdapterEnrollment(Context context, ArrayList<Student_info> enrollments) {
            this.enrollments = enrollments;
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerAdapterEnrollment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.custome_design,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapterEnrollment.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            Student_info s = enrollments.get(position);
            holder.textView.setText(s.getSenroll());
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Clicked on " + enrollments.get(position).getSenroll(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return enrollments.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textName);
            }
        }
}




