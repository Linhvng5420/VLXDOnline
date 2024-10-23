package com.tdc.vlxdonline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tdc.vlxdonline.R;

public class Warehouse_Kho_HomeActivity extends AppCompatActivity {
    TextView tvQLSP,tvQLDVDM,tvQLBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kho_home_layout);
        setCtronl();
        setEvent();
    }

    private void setEvent() {
        tvQLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Warehouse_Kho_HomeActivity.this, Warehouse_ThemSanPhamActivity.class);
                startActivity(intent);
            }
        });
        tvQLDVDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Warehouse_Kho_HomeActivity.this, Warehouse_DanhMucDonViActivity.class);
                startActivity(intent);
            }
        });
        tvQLBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Warehouse_Kho_HomeActivity.this, Warehouse_AnhSP_BannerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setCtronl() {
        tvQLSP = findViewById(R.id.tvQLSP);
        tvQLDVDM = findViewById(R.id.tvQLDVDM);
        tvQLBanner = findViewById(R.id.tvQLBanner);
    }
}
