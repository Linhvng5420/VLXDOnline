package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

public class Owner_NhanVienDetailFragment extends Fragment {

    private NhanVien selectedNhanVien;
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        nhanvienDetailBinding = FragmentOwnerNhanvienDetailBinding.inflate(inflater, container, false);
        return nhanvienDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Thiết lập Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Hiển thị nút "quay về"
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý sự kiện khi nhấn nút quay về
        toolbar.setNavigationOnClickListener(v -> {
            // Quay về Fragment trước đó
            getParentFragmentManager().popBackStack();
        });

        // Lấy dữ liệu từ Bundle
        if (getArguments() != null) {
            selectedNhanVien = (NhanVien) getArguments().getSerializable("selectedNhanVien");

            // Hiển thị thông tin nhân viên
            Log.d("selectedNhanVien", selectedNhanVien.toString());

            nhanvienDetailBinding.tvTenNhanVien.setText(selectedNhanVien.getTenNV());
            nhanvienDetailBinding.tvChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");
            nhanvienDetailBinding.tvSdt.setText(selectedNhanVien.getSdt());
            nhanvienDetailBinding.tvEmail.setText(selectedNhanVien.getEmail());
        }
    }
}