package com.tdc.vlxdonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tdc.vlxdonline.Model.SanPham_Model;
import com.tdc.vlxdonline.R;

import java.util.List;

public class SanPham_Adapter extends RecyclerView.Adapter<SanPham_ViewHolder> {
    private Context context;
    private List<SanPham_Model> list;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SanPham_Adapter(Context context, List<SanPham_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SanPham_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsp_layout, parent, false);
        return new SanPham_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPham_ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImages()).into(holder.ivImages);
        holder.tvTenSP.setText(list.get(position).getTenSP());
        holder.giabanSP.setText(list.get(position).giabanSP);
        // Thiết lập sự kiện click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition()); // Dùng getAdapterPosition() thay vì position
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
