package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdc.vlxdonline.Adapter.NhanVienAdapter;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienBinding;

import java.util.ArrayList;
import java.util.List;

public class Owner_NhanVienFragment extends Fragment {

    private FragmentOwnerNhanvienBinding ownerNhanvienBinding;
    private NhanVienAdapter nhanVienAdapter;
    private List<NhanVien> nhanVienList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Sử dụng View Binding cho Fragment
        ownerNhanvienBinding = FragmentOwnerNhanvienBinding.inflate(inflater, container, false);
        return ownerNhanvienBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Thiết lập RecyclerView
        ownerNhanvienBinding.ownerRcvNhanVien.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sử dụng phương thức generateDummyData() để tạo dữ liệu
        nhanVienList = generateDummyData();

        // Thiết lập Adapter
        nhanVienAdapter = new NhanVienAdapter(nhanVienList);
        ownerNhanvienBinding.ownerRcvNhanVien.setAdapter(nhanVienAdapter);
    }

    // Phương thức tạo 50 dữ liệu mẫu
    private List<NhanVien> generateDummyData() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            // ChucVu chỉ nhận 0 hoặc 1, sử dụng i % 2 để tạo xen kẽ 0 và 1
            int chucVu = i % 2;
            nhanVienList.add(new NhanVien(i, 1, "Nhan Vien " + i, chucVu, "0123456789" + i, "nhanvien" + i + "@example.com"));
        }
        return nhanVienList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ownerNhanvienBinding = null; // Giải phóng binding để tránh rò rỉ bộ nhớ
    }
}
