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

public class RecyclerAdapterDates extends RecyclerView.Adapter<RecyclerAdapterDates.ViewHolder>
{

    static ArrayList<String> data;
    //String data[];
    Context context;
    // private static ViewHolder.RecyclerViewClickListener listener;
    static int pos;
    private RecyclerViewClickListener listener;

    public RecyclerAdapterDates() {
    }

    public RecyclerAdapterDates(Context context, ArrayList<String> data, RecyclerViewClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerAdapterDates.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custome_design,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterDates.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //Attendance attendance = data.get(position);
        holder.textView.setText(data.get(position).toUpperCase());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pos = position;
                Toast.makeText(context,"Clicked on " + data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.textName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }

//        public interface RecyclerViewClickListener{
//            void onCLick(View v, int position);
//        }
    }
}



