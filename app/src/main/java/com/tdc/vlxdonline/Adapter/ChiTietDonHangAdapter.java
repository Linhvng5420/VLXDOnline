package com.tdc.vlxdonline.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tdc.vlxdonline.Model.ChiTietDon;
import com.tdc.vlxdonline.databinding.ItemDetailDonBinding;

import java.util.ArrayList;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ChiTietDonHolder> {

    Activity context;
    ArrayList<ChiTietDon> data;
    OnChiTietDonClick onChiTietDonClick;

    public void setOnChiTietDonClick(OnChiTietDonClick onChiTietDonClick) {
        this.onChiTietDonClick = onChiTietDonClick;
    }

    public ChiTietDonHangAdapter(Activity context, ArrayList<ChiTietDon> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ChiTietDonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChiTietDonHolder(ItemDetailDonBinding.inflate(context.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHolder holder, int position) {
        ChiTietDon chiTiet = data.get(position);

        Glide.with(context).load(chiTiet.getAnh()).into(holder.binding.imgDetailDon);
        holder.binding.tvNameDetailDon.setText(chiTiet.getTen());
        holder.binding.tvGiaDetailDon.setText(String.format("%d đ", chiTiet.getGia()));
        holder.binding.tvSlDetailDon.setText(String.format("Số Lượng: %d", chiTiet.getSoLuong()));
        int tong = Integer.parseInt(chiTiet.getSoLuong()) * Integer.parseInt(chiTiet.getGia());
        holder.binding.tvTongDetailDon.setText(String.format("Tổng: %d", tong));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChiTietDonHolder extends RecyclerView.ViewHolder {

        ItemDetailDonBinding binding;
        int position;

        public ChiTietDonHolder(@NonNull ItemDetailDonBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onChiTietDonClick != null) {
                        onChiTietDonClick.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnChiTietDonClick{
        void onItemClick(int position);
    }
}
