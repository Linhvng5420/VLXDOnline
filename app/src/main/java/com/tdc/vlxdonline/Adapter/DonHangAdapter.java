package com.tdc.vlxdonline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tdc.vlxdonline.Model.DonHang;
import com.tdc.vlxdonline.databinding.ItemDonHangBinding;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangHolder> {

    private Context context;
    private ArrayList<DonHang> data;
    OnItemDonHangClick onItemDonHangClick;

    public void setOnItemDonHangClick(OnItemDonHangClick onItemDonHangClick) {
        this.onItemDonHangClick = onItemDonHangClick;
    }

    public DonHangAdapter(Context context, ArrayList<DonHang> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DonHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonHangHolder(ItemDonHangBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangHolder holder, int position) {
        DonHang donHang = data.get(position);

        Glide.with(context).load(donHang.getAnh()).into(holder.binding.ivAnhDon);
        holder.binding.tvTenNhanDon.setText(donHang.getTenKhach());
        holder.binding.tvSdtDon.setText(donHang.getSdt());
        holder.binding.tvDiaChiDon.setText(donHang.getDiaChi());
        holder.binding.tvTongGia.setText(String.format("Tổng Tiền: %d", donHang.getTongTien()));
        // Sử lý hiển thị trạng thái
        if (donHang.getTrangThai() == 0) {
            holder.binding.tvTrangThaiVc.setBackgroundColor(Color.rgb(255, 0, 0));
            holder.binding.tvTrangThaiVc.setText("Chờ Xác Minh");
        }else if (donHang.getTrangThai() == 1) {
            holder.binding.tvTrangThaiVc.setBackgroundColor(Color.rgb(0, 0, 255));
            holder.binding.tvTrangThaiVc.setText("Chờ Nhận Giao Hàng");
        }else if (donHang.getTrangThai() == 2) {
            holder.binding.tvTrangThaiVc.setBackgroundColor(Color.rgb(0, 255, 255));
            holder.binding.tvTrangThaiVc.setText("Đang Vận Chuyển");
        }else if (donHang.getTrangThai() == 3) {
            holder.binding.tvTrangThaiVc.setBackgroundColor(Color.rgb(255, 255, 0));
            holder.binding.tvTrangThaiVc.setText("Chờ Nhận Hàng");
        }else if (donHang.getTrangThai() == 4) {
            holder.binding.tvTrangThaiVc.setBackgroundColor(Color.rgb(255, 0, 0));
            if (donHang.getTrangThaiTT() == 0) {
                holder.binding.tvTrangThaiVc.setText("Chưa Thanh Toán");
            } else if (donHang.getTrangThaiTT() == 1) {
                holder.binding.tvTrangThaiVc.setText("Đang Trả Góp");
            } else if (donHang.getTrangThaiTT() == 2) {
                holder.binding.tvTrangThaiVc.setBackgroundColor(Color.rgb(0, 255, 0));
                holder.binding.tvTrangThaiVc.setText("Đã Hoàn Thành");
            }
        }

        final int pos = position;
        holder.position = pos;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DonHangHolder extends RecyclerView.ViewHolder {

        ItemDonHangBinding binding;
        int position;

        public DonHangHolder(@NonNull ItemDonHangBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemDonHangClick != null) {
                        onItemDonHangClick.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnItemDonHangClick{
        void onItemClick(int position);
    }
}
