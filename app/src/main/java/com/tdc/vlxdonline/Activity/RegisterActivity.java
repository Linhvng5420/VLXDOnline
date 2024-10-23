package com.tdc.vlxdonline.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tdc.vlxdonline.Adapter.AdapterCenterDrop;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ActivityRegisterBinding;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    ArrayList<String> dataType = new ArrayList();
    AdapterCenterDrop adap;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setEvents();
    }

    private void setEvents() {
        KhoiTao();
        binding.btnRg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        binding.spRoleRg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean Register() {
        String pass = binding.edtPassRg.getText().toString();
        String rePass = binding.edtRePassRg.getText().toString();
        String email = binding.edtEmailRg.getText().toString();
        String sdt = binding.edtSDTRg.getText().toString();
        String hoten = binding.edtHoTenRg.getText().toString();
        if (!pass.isEmpty() && !rePass.isEmpty() && !email.isEmpty() && !sdt.isEmpty() && !hoten.isEmpty()) {
            if(!pass.equals(rePass)){
                Toast.makeText(this, "Nhập Lại Mật Khẩu Sai!", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                Toast.makeText(getApplicationContext(), "Đăng Ký Thành Công!", Toast.LENGTH_SHORT).show();
                onBackPressed();
                return true;
            }
        }else{
            Toast.makeText(this, "Chưa Nhập Đủ Thông Tin!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void KhoiTao() {
        dataType.add("Chủ Cửa Hàng");
        dataType.add("Khách Hàng");
        adap = new AdapterCenterDrop(this, R.layout.item_center_drop, dataType);
        binding.spRoleRg.setAdapter(adap);
    }
}