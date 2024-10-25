package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tdc.vlxdonline.Model.ChiTietDon;
import com.tdc.vlxdonline.Model.DonHang;
import com.tdc.vlxdonline.Model.Products;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentDatHangNgayBinding;

public class DatHangNgayFragment extends Fragment {

    private FragmentDatHangNgayBinding binding;
    private DonHang donHang;
    private ChiTietDon chiTietDon;
    private Products product;
    private int soLuong;

    public DatHangNgayFragment(Products product, int soLuong) {
        this.product = product;
        this.soLuong = soLuong;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDatHangNgayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KhoiTao();
        // Su Kien Tang Giam SL
        binding.btnGiamDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int so = Integer.parseInt(binding.edtSlDat.getText().toString());
                if (so > 1) {
                    binding.edtSlDat.setText((so - 1) + "");
                    int tong = Integer.parseInt(product.getGia()) * Integer.parseInt(binding.edtSlDat.getText().toString());
                    binding.tvTongDatNgay.setText(chuyenChuoi(tong));
                }
            }
        });
        binding.btnTangDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int so = Integer.parseInt(binding.edtSlDat.getText().toString());
                binding.edtSlDat.setText((so + 1) + "");
                int tong = Integer.parseInt(product.getGia()) * Integer.parseInt(binding.edtSlDat.getText().toString());
                binding.tvTongDatNgay.setText(chuyenChuoi(tong));
            }
        });
        // Su kien nhap so luong
        binding.edtSlDat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String chuoi = binding.edtSlDat.getText().toString();
                if (!chuoi.isEmpty()) {
                    for (int i = 0; i < chuoi.length(); i++) {
                        if (chuoi.charAt(i) < '0' || chuoi.charAt(i) > '9') {
                            binding.edtSlDat.setText("");
                            Toast.makeText(getActivity(), "Chỉ được nhập số!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    binding.edtSlDat.setText("1");
                }
                int tong = Integer.parseInt(product.getGia()) * Integer.parseInt(binding.edtSlDat.getText().toString());
                binding.tvTongDatNgay.setText(chuyenChuoi(tong));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // Su kien mua ngay
        binding.btnDatNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private StringBuilder chuyenChuoi(int soTien){
        StringBuilder chuoi = new StringBuilder(soTien + "");
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
        return chuoi;
    }

    private void KhoiTao() {
        Glide.with(getActivity()).load(product.getAnh()).into(binding.imgDatHangNgay);
        binding.tvNameDatHangNgay.setText(product.getTen());
        binding.tvGiaDatHangNgay.setText(chuyenChuoi(Integer.parseInt(product.getGia())) + " đ");
        binding.tvDesDatNgay.setText(product.getMoTa());
        binding.edtSlDat.setText(soLuong + "");
        binding.tvTongDatNgay.setText(chuyenChuoi(Integer.parseInt(product.getGia()) * soLuong));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}