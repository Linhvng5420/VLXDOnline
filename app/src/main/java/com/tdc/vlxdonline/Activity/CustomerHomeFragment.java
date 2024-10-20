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
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.tdc.vlxdonline.Adapter.CategoryAdapter;
import com.tdc.vlxdonline.Adapter.ProductAdapter;
import com.tdc.vlxdonline.Model.Categorys;
import com.tdc.vlxdonline.Model.Products;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentCustomerHomeBinding;

import java.util.ArrayList;

public class CustomerHomeFragment extends Fragment {

    FragmentCustomerHomeBinding binding;
    ArrayList<Products> dataProds = new ArrayList<>();
    ProductAdapter productAdapter;
    ArrayList<Categorys> dataCategorys = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    ArrayList<String> dataLoc = new ArrayList<>();
    ArrayAdapter adapterLoc;
    ArrayList<String> dataSapXep = new ArrayList<>();
    ArrayAdapter adapterSX;


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
    }

    private void KhoiTao() {

        dataCategorys.add(new Categorys("Cate 1", 1, R.drawable.tc));
        dataCategorys.add(new Categorys("Cate 2", 2, R.drawable.nav_sanpham));
        dataCategorys.add(new Categorys("Cate 3", 3, R.drawable.star));
        dataCategorys.add(new Categorys("Cate 4", 4, R.drawable.search));
        dataProds.add(new Products("A", "A", "A", 1, 1, R.drawable.tc, 100000, 0.0, 1, 1000, 580));
        dataProds.add(new Products("B", "B", "B", 1, 2, R.drawable.tc, 200000, 4.0, 1, 1000, 580));
        dataProds.add(new Products("C", "C", "C", 1, 3, R.drawable.tc, 300000, 5.0, 1, 1000, 580));
        dataProds.add(new Products("D", "D", "D", 1, 4, R.drawable.tc, 400000, 3.5, 1, 1000, 580));

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

        setHienThiSanPham();

    }

    private void setHienThiSanPham() {
        productAdapter = new ProductAdapter(getActivity(), dataProds, View.VISIBLE);
        productAdapter.setOnItemProductClickListener(new ProductAdapter.OnItemProductClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Products product = dataProds.get(position);
                ((Customer_HomeActivity) getActivity()).ReplaceFragment(new ProdDetailCustomerFragment(product.getId()));
            }

            @Override
            public void OnBtnBuyClick(View view, int position) {

            }
        });
        binding.rcProdCustomerHome.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rcProdCustomerHome.setAdapter(productAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}