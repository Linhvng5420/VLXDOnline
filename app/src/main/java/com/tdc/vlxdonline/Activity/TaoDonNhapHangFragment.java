package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.tdc.vlxdonline.Adapter.ProductAdapter;
import com.tdc.vlxdonline.Model.ChiTietNhap;
import com.tdc.vlxdonline.Model.Products;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentTaoDonNhapHangBinding;

import java.util.ArrayList;

public class TaoDonNhapHangFragment extends Fragment {

    FragmentTaoDonNhapHangBinding binding;
    ArrayList<Products> dsSanPham = new ArrayList<>();
    ProductAdapter adapter;
    ArrayList<ChiTietNhap> dsChiTiet = new ArrayList<>();
    Products products = new Products();
    Button btnNext1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setEvent();
    }

    private void setEvent() {
//        dsSanPham.add(new Products("Thép", "bao", " ", 1111, 1, R.drawable.thep, 95000, 0, 1, 1, 1));
//        dsSanPham.add(new Products("Xi Măng", "bao", " ", 1111, 1, R.drawable.ximang, 70000, 0, 1, 1, 1));
//        dsSanPham.add(new Products("Tôn", "bao", " ", 1111, 1, R.drawable.ton, 50000, 0, 1, 1, 1));
//        dsSanPham.add(new Products("Đá Mi", "bao", " ", 1111, 1, R.drawable.dami, 75000, 0, 1, 1, 1));


        adapter = new ProductAdapter(getActivity(), dsSanPham, View.GONE);
        adapter.setOnItemProductClickListener(new ProductAdapter.OnItemProductClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                products = dsSanPham.get(position);
                Toast.makeText(getActivity(), "Da chon san pham " + products.getTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnBtnBuyClick(View view, int position) {

            }
        });

        binding.rcvSanpham.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        binding.rcvSanpham.setAdapter(adapter);

        binding.svTimSanPham.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getActivity(), "" + s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        // Thêm sự kiện cho nút chuyển trang
        binding.btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để chuyển sang Fragment khác
                ((Warehouse_HomeActivity) getActivity()).ReplaceFragment(new ThongTinNhanHang_Fragment());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTaoDonNhapHangBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}