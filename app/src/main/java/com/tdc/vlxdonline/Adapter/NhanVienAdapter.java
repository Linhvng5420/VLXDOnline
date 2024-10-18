package com.tdc.vlxdonline.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.databinding.ItemOwnerNhanvienRcvBinding;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {

    private List<NhanVien> nhanVienList;
    private OnItemClickListener onItemClickListener;

    // Constructor
    public NhanVienAdapter(List<NhanVien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    public interface OnItemClickListener {
        void onItemClick(NhanVien nhanVien);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sử dụng View Binding cho dữ liệu nhân viên
        ItemOwnerNhanvienRcvBinding binding = ItemOwnerNhanvienRcvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NhanVienViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienViewHolder holder, int position) {
        NhanVien nhanVien = nhanVienList.get(position);
        holder.bind(nhanVien);

        // Xử lý sự kiện nhấn vào item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(nhanVien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nhanVienList.size();
    }

    // ViewHolder cho dữ liệu NhanVien sử dụng View Binding
    public static class NhanVienViewHolder extends RecyclerView.ViewHolder {
        private final ItemOwnerNhanvienRcvBinding binding;

        public NhanVienViewHolder(@NonNull ItemOwnerNhanvienRcvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NhanVien nhanVien) {
            binding.tvId.setText(String.valueOf(nhanVien.getId()));
            binding.tvTenNV.setText(nhanVien.getTenNV());

            // Hiển thị chức vụ: 0 là Kho, 1 là Giao Hàng
            String chucVuText = nhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng";
            binding.tvChucVu.setText(chucVuText);
        }
    }
}
