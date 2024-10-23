package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.tdc.vlxdonline.Adapter.AdapterCenterDrop;
import com.tdc.vlxdonline.Adapter.CategoryAdapter;
import com.tdc.vlxdonline.Adapter.ProductAdapter;
import com.tdc.vlxdonline.Model.Categorys;
import com.tdc.vlxdonline.Model.Products;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentCustomerHomeBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomerHomeFragment extends Fragment {

    FragmentCustomerHomeBinding binding;
    ArrayList<Products> dataProds = new ArrayList<>();
    ProductAdapter productAdapter;
    ArrayList<Categorys> dataCategorys = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    ArrayList<String> dataLoc = new ArrayList<>();
    AdapterCenterDrop adapterLoc;
    ArrayList<String> dataSapXep = new ArrayList<>();
    AdapterCenterDrop adapterSX;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerHomeBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KhoiTao();
        // Su kien search
        binding.svCustomerHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // Category Adapter
        categoryAdapter = new CategoryAdapter(getActivity(), dataCategorys);
        categoryAdapter.setOnItemCategoryClickListener(new CategoryAdapter.OnItemCategoryClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rcDanhMuc.setLayoutManager(linearLayoutManager);
        binding.rcDanhMuc.setAdapter(categoryAdapter);
        // Su kien loc
        binding.spLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Su kien sap xep
        binding.spXapSep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SapXepDanhSach(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setHienThiSanPham();
    }

    private void KhoiTao() {

        dataLoc.add("Lọc Theo");
        dataLoc.add("Số sao từ 1 - 3");
        dataLoc.add("Số sao từ 4 - 5");
        dataLoc.add("Giá nhỏ hơn trung bình");
        dataLoc.add("Giá lớn hơn trung bình");
        adapterLoc = new AdapterCenterDrop(getActivity(), R.layout.item_center_drop, dataLoc);
        binding.spLoc.setAdapter(adapterLoc);

        dataSapXep.add("Sắp Xếp Theo");
        dataSapXep.add("Số sao Tăng Dần");
        dataSapXep.add("Số sao Giảm Dần");
        dataSapXep.add("Giá Tăng Dần");
        dataSapXep.add("Giá Giảm Dần");
        adapterSX = new AdapterCenterDrop(getActivity(), R.layout.item_center_drop, dataSapXep);
        binding.spXapSep.setAdapter(adapterSX);

        dataCategorys.add(new Categorys("Cate 1", "1", "https://th.bing.com/th?id=OIF.0yfM4yF7hYIDB6%2bmwxU4GQ&w=172&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"));
        dataCategorys.add(new Categorys("Cate 2", "2", "https://th.bing.com/th/id/OIF.lf4QHwb4DMz5wHZ84QYJmQ?w=183&h=183&c=7&r=0&o=5&dpr=1.3&pid=1.7"));
        dataCategorys.add(new Categorys("Cate 3", "3", "https://th.bing.com/th/id/OIP.UWORqopZEI954B5G-Z4sbgHaHQ?w=169&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"));
        dataCategorys.add(new Categorys("Cate 4", "4", "https://th.bing.com/th/id/OIP.BO1VNjeGOUGcGRWQNUVCZQHaHa?w=1024&h=1024&rs=1&pid=ImgDetMain"));
        dataProds.add(new Products("A", "A", "A", "1", "1", "https://th.bing.com/th/id/OIP.UWORqopZEI954B5G-Z4sbgHaHQ?w=169&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7", "100000", "0.0", "1", "1000", "580"));
        dataProds.add(new Products("B", "B", "B", "1", "2", "https://th.bing.com/th/id/OIP.BO1VNjeGOUGcGRWQNUVCZQHaHa?w=1024&h=1024&rs=1&pid=ImgDetMain", "200000", "4.0", "1", "1000", "580"));
        dataProds.add(new Products("C", "C", "C", "1", "3", "https://th.bing.com/th/id/OIP.vyMrfzra1TPcklie3-GA9gHaH9?w=180&h=183&c=7&r=0&o=5&dpr=1.3&pid=1.7", "300000", "5.0", "1", "1000", "580"));
        dataProds.add(new Products("D", "D", "D", "1", "4", "https://th.bing.com/th?id=OIF.EGFQW6dgdgP%2fL6l2yvVChg&rs=1&pid=ImgDetMain", "400000", "3.5", "1", "1000", "580"));
    }

    private void setHienThiSanPham() {
        productAdapter = new ProductAdapter(getActivity(), dataProds, View.VISIBLE);
        productAdapter.setOnItemProductClickListener(new ProductAdapter.OnItemProductClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Products product = dataProds.get(position);
                ((Customer_HomeActivity) getActivity()).ReplaceFragment(new ProdDetailCustomerFragment(product));
            }

            @Override
            public void OnBtnBuyClick(View view, int position) {

            }
        });
        binding.rcProdCustomerHome.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rcProdCustomerHome.setAdapter(productAdapter);
    }

    private void SapXepDanhSach(int type){
        Collections.sort(dataProds, new Comparator<Products>() {
            @Override
            public int compare(Products p1, Products p2) {
                int gia1 = Integer.parseInt(p1.getGia());
                int gia2 = Integer.parseInt(p2.getGia());
                double sao1 = Double.parseDouble(p1.getSoSao());
                double sao2 = Double.parseDouble(p2.getSoSao());
                if (type == 1) {
                    return Double.compare(sao1, sao2);
                } else if (type == 2) {
                    return Double.compare(sao2, sao1);
                } else if (type == 3) {
                    return Integer.compare(gia1, gia2);
                } else if (type == 4) {
                    return Integer.compare(gia2, gia1);
                }
                return 0;
            }
        });
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}