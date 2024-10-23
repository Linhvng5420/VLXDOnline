package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdc.vlxdonline.Adapter.ChiTietDonHangAdapter;
import com.tdc.vlxdonline.Model.ChiTietDon;
import com.tdc.vlxdonline.Model.DonHang;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentChiTietDonBinding;

import java.util.ArrayList;

public class ChiTietDonFragment extends Fragment {

    FragmentChiTietDonBinding binding;
    private DonHang donHang;
    ArrayList<ChiTietDon> dataChiTietDon = new ArrayList<>();
    ChiTietDonHangAdapter adapter;

    public ChiTietDonFragment(DonHang donHang) {
        this.donHang = donHang;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChiTietDonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KhoiTao();
        // Xac nhan khach hang da thanh toan
        binding.btnTrangThaiTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Bat su kien nut Trang Thai Van Chuyen (Nhieu loai su kien, dua vao doi tuong dang dung)
        binding.btnTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trangThaiVc = Integer.parseInt(donHang.getTrangThai());
                int trangThaiTt = Integer.parseInt(donHang.getTrangThaiTT());

//                Update điều kiện typeUser = 2 thành typeEmployee = 1 khi có nhân viên giao hàng
                if (LoginActivity.typeUser == 2 && trangThaiVc == 1) {

                } else if (LoginActivity.typeUser == 2 && trangThaiVc == 2) {

                } else if (LoginActivity.typeUser == 1) {

                }
            }
        });
    }

    private void KhoiTao() {
        binding.tvTenNguoiNhan.setText(donHang.getTenKhach());
        binding.tvSdtNguoiNhan.setText(donHang.getSdt());
        binding.tvDiaChiNhan.setText(donHang.getDiaChi());
        binding.btnTrangThai.setEnabled(false);
        binding.btnTrangThaiTt.setEnabled(false);
        binding.btnTrangThaiTt.setVisibility(View.GONE);

        StringBuilder chuoi = new StringBuilder(donHang.getTongTien());
        if (chuoi.length() > 3) {
            int dem = 0;
            int doDai = chuoi.length() - 1;
            for (int i = doDai; i > 0; i--) {
                dem = dem + 1;
                if (dem == 3) {
                    chuoi.insert(i, '.');
                    dem = 0;
                }
            }
        }
        binding.tvTongTien.setText(chuoi);

        int trangThaiVc = Integer.parseInt(donHang.getTrangThai());
        int trangThaiTt = Integer.parseInt(donHang.getTrangThaiTT());
        // Update điều kiện typeUser = 2 thành typeEmployee = 1 khi có nhân viên giao hàng
        if (trangThaiVc == 0) {
            binding.btnTrangThai.setText(R.string.cho_xac_nhan);
        } else {
            // Neu chua thanh toan va nguoi dung la chu thi hien thi nut xac nhan thanh toan
            if (LoginActivity.typeUser == 0 && trangThaiTt == 0) {
                binding.btnTrangThaiTt.setVisibility(View.VISIBLE);
                binding.btnTrangThaiTt.setEnabled(true);
                binding.btnTrangThaiTt.setText("Xác Nhận Đã Thanh Toán");
            }
            if (trangThaiVc == 1) {
                binding.btnTrangThai.setText(R.string.cho_nhan_don);
                if (LoginActivity.typeUser == 2) {
                    binding.btnTrangThai.setEnabled(true);
                    binding.btnTrangThai.setText("Nhận Giao Đơn Hàng");
                }
            } else if (trangThaiVc == 2) {
                binding.btnTrangThai.setText(R.string.dang_van_chuyen);
                if (LoginActivity.typeUser == 2) {
                    binding.btnTrangThai.setEnabled(true);
                    binding.btnTrangThai.setText("Xác Nhận Giao Thành Công");
                }
            } else if (trangThaiVc == 3) {
                binding.btnTrangThai.setText(R.string.cho_nhan_hang);
                if (LoginActivity.typeUser == 1) {
                    binding.btnTrangThai.setEnabled(true);
                    binding.btnTrangThai.setText("Xác Nhận Đã Nhận Hàng");
                }
            } else if (trangThaiVc == 4) {
                binding.btnTrangThai.setText(R.string.da_hoan_thanh);
                if (trangThaiTt == 0) {
                    binding.btnTrangThai.setText(R.string.chua_thanh_toan);
                }
            }
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}