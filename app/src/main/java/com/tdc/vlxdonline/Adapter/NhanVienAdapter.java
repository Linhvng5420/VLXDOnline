package com.tdc.vlxdonline.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.databinding.ItemOwnerNhanvienRcvBinding;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {

    // Danh sách chứa dữ liệu nhân viên
    private List<NhanVien> nhanVienList;

    // Biến callback cho sự kiện nhấn vào item
    private OnItemClickListener onItemClickListener;

    // Constructor để truyền dữ liệu nhân viên vào adapter
    public NhanVienAdapter(List<NhanVien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    // Giao diện cho sự kiện nhấn vào item
    public interface OnItemClickListener {
        void onItemClick(NhanVien nhanVien); // Phương thức được gọi khi một item được nhấn
    }

    // Phương thức thiết lập listener cho sự kiện nhấn vào item
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    // Tạo ViewHolder bằng View Binding để hiển thị item nhân viên
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sử dụng View Binding để inflate layout item nhân viên
        ItemOwnerNhanvienRcvBinding binding = ItemOwnerNhanvienRcvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NhanVienViewHolder(binding); // Trả về một ViewHolder mới với binding
    }

    @Override
    // Gán dữ liệu nhân viên vào ViewHolder cho từng item
    public void onBindViewHolder(@NonNull NhanVienViewHolder holder, int position) {
        NhanVien nhanVien = nhanVienList.get(position); // Lấy nhân viên tại vị trí 'position'
        holder.bind(nhanVien); // Gọi phương thức bind để hiển thị dữ liệu nhân viên

        // Xử lý sự kiện nhấn vào item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(nhanVien); // Gọi phương thức onItemClick khi item được nhấn
            }
        });
    }

    @Override
    // Trả về số lượng item trong danh sách nhân viên
    public int getItemCount() {
        return nhanVienList.size();
    }

    // Phương thức để lấy danh sách nhân viên
    public List<NhanVien> getNhanVienList() {
        return nhanVienList;
    }

    // ViewHolder cho dữ liệu nhân viên sử dụng View Binding
    public static class NhanVienViewHolder extends RecyclerView.ViewHolder {
        // Binding liên kết với layout của từng item nhân viên
        private final ItemOwnerNhanvienRcvBinding binding;

        // Constructor ViewHolder, nhận đối tượng binding
        public NhanVienViewHolder(@NonNull ItemOwnerNhanvienRcvBinding binding) {
            super(binding.getRoot()); // Gọi hàm super để liên kết View gốc
            this.binding = binding;
        }

        // Phương thức bind để gán dữ liệu nhân viên vào các view trong item
        public void bind(NhanVien nhanVien) {
            // Hiển thị ID của nhân viên
            binding.tvId.setText(String.valueOf(nhanVien.getID()));

            // Hiển thị tên nhân viên
            binding.tvTenNV.setText(nhanVien.getTenNV());

            // Hiển thị chức vụ: 0 là Kho, 1 là Giao Hàng
            String chucVuText = nhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng";
            binding.tvChucVu.setText(chucVuText); // Gán giá trị chức vụ vào TextView
        }
    }
}


