package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdc.vlxdonline.Adapter.DonHangAdapter;
import com.tdc.vlxdonline.Adapter.ProductAdapter;
import com.tdc.vlxdonline.Model.ChiTietNhap;
import com.tdc.vlxdonline.Model.Products;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentGiaoDienKhoBinding;

import java.util.ArrayList;
import java.util.List;


public class GiaoDienKho_Fragment extends Fragment {

    FragmentGiaoDienKhoBinding binding;
    ArrayList<Products> dsSanPham = new ArrayList<>();
    ProductAdapter adapter;
    ArrayList<ChiTietNhap> dsChiTiet = new ArrayList<>();
    Products products = new Products();
    RecyclerView recyclerView;
    ArrayList<Products> list = new ArrayList<>();
    DatabaseReference reference;
    ValueEventListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getData();
        setEvent();
    }

    private void setEvent() {
//        dsSanPham.add(new Products("Thép", "bao", " ", 1111, 1, R.drawable.thep, 95000, 0, 1, 1, 1));
//        dsSanPham.add(new Products("Thép", "bao", " ", 1111, 1, R.drawable.thep, 95000, 0, 1, 1, 1));
//        dsSanPham.add(new Products("Thép", "bao", " ", 1111, 1, R.drawable.thep, 95000, 0, 1, 1, 1));
//        dsSanPham.add(new Products("Thép", "bao", " ", 1111, 1, R.drawable.thep, 95000, 0, 1, 1, 1));


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

        binding.rcvSanphamkho.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        binding.rcvSanphamkho.setAdapter(adapter);


    }
//    private void getData() {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        adapter = new ProductAdapter(getActivity(), list, View.GONE);
//        recyclerView.setAdapter(adapter);
//        reference = FirebaseDatabase.getInstance().getReference("SanPham");
//        listener = reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot items : snapshot.getChildren()) {
//                    Products sanPhamModel = items.getValue(Products.class);
//                    list.add(sanPhamModel);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
//    }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            binding = FragmentGiaoDienKhoBinding.inflate(inflater, container, false);
            // Inflate the layout for this fragment
            return binding.getRoot();
        }
    }