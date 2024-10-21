package com.tdc.vlxdonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tdc.vlxdonline.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> data;
    OnItemImageClick onItemImageClick;

    public void setOnItemImageClick(OnItemImageClick onItemImageClick) {
        this.onItemImageClick = onItemImageClick;
    }

    public ImageAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(data.get(position)).into(holder.img);
        final int pos = position;
        holder.position = pos;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        int position;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_v_img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemImageClick != null) {
                        onItemImageClick.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnItemImageClick{
        void onItemClick(int position);
    }
}
