package com.tdc.vlxdonline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.tdc.vlxdonline.Adapter.SanPham_Adapter;
import com.tdc.vlxdonline.Model.SanPham_Model;
import com.tdc.vlxdonline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Warehouse_HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<SanPham_Model> list = new ArrayList<>();
    SanPham_Adapter adapter;
    DatabaseReference reference;
    ValueEventListener listener;
    ImageButton ivbtnthem;
//   SwipeableRecyclerView recyclerView;

    // SanPham_Model sanPhamModel = new SanPham_Model(edtNhapten.getText(), edtNhapgianhap.getText(), edtNhapgianhap.getText(), edtNhapgianhap.getText(), edtNhapgianhap.getText(), edtNhapgianhap.getText());
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_home);
        setControl();
        getDate();
        setEvent();

    }

    private void setEvent() {

    }

    private void getDate() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SanPham_Adapter(Warehouse_HomeActivity.this, list);
        recyclerView.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference("SanPham");
        listener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot items : snapshot.getChildren()) {
                    SanPham_Model sanPhamModel = items.getValue(SanPham_Model.class);
                    list.add(sanPhamModel);
                    // String abc = String.valueOf(items.child("images").getValue(String.class));
                    //tvTest.setText(String.valueOf(list));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        ivbtnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Warehouse_HomeActivity.this, ThemSanPham.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        ivbtnthem = findViewById(R.id.ivbtnthem);
        recyclerView = findViewById(R.id.recycleview);
    }
}
