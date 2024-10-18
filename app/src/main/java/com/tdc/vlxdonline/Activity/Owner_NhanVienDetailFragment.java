package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

public class Owner_NhanVienDetailFragment extends Fragment {

    // Khai báo đối tượng NhanVien để lưu trữ thông tin nhân viên được chọn
    private NhanVien selectedNhanVien;

    // Khai báo đối tượng binding để tương tác với các thành phần trong giao diện (layout fragment_owner_nhanvien_detail.xml)
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Phương thức onCreate để khởi tạo fragment, gọi super để sử dụng các phương thức của Fragment
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Gán binding cho layout fragment_owner_nhanvien_detail.xml bằng cách sử dụng phương thức inflate()
        nhanvienDetailBinding = FragmentOwnerNhanvienDetailBinding.inflate(inflater, container, false);

        // Trả về root của binding, tức là toàn bộ giao diện của fragment
        return nhanvienDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Phương thức này được gọi sau khi view đã được tạo
        super.onViewCreated(view, savedInstanceState);

        // Thiết lập Toolbar cho Fragment (sử dụng Toolbar trong giao diện để thay thế ActionBar)
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Kiểm tra và hiển thị nút "quay về" trên Toolbar nếu ActionBar tồn tại
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            // Hiển thị nút quay về trên ActionBar
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Hiển thị nút quay về trên Toolbar
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý sự kiện khi nhấn nút quay về trên Toolbar
        toolbar.setNavigationOnClickListener(v -> {
            // Quay về Fragment trước đó trong ngăn xếp (back stack)
            getParentFragmentManager().popBackStack();
        });

        // Lấy thông tin từ đối tượng Bundle, kiểm tra nếu Bundle có dữ liệu được truyền vào
        if (getArguments() != null) {
            // Lấy thông tin nhân viên từ Bundle bằng cách chuyển đổi đối tượng Serializable
            selectedNhanVien = (NhanVien) getArguments().getSerializable("selectedNhanVien");

            // Ghi lại thông tin nhân viên trong log để kiểm tra
            Log.d("selectedNhanVien", selectedNhanVien.toString());

            // Hiển thị thông tin nhân viên lên các thành phần giao diện (TextView) thông qua binding
            nhanvienDetailBinding.etTenNhanVien.setText(selectedNhanVien.getTenNV());
            // Hiển thị chức vụ nhân viên, nếu giá trị ChucVu là 0 thì hiển thị "Kho", nếu là 1 thì hiển thị "Giao Hàng"
            nhanvienDetailBinding.etChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");
            // Hiển thị số điện thoại của nhân viên
            nhanvienDetailBinding.etSDT.setText(selectedNhanVien.getSdt());
            // Hiển thị email của nhân viên
            nhanvienDetailBinding.etEmail.setText(selectedNhanVien.getEmail());
        }
    }
}
