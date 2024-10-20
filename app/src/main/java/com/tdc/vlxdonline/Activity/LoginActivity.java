package com.tdc.vlxdonline.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.tdc.vlxdonline.Model.Categorys;
import com.tdc.vlxdonline.Model.DonVi;
import com.tdc.vlxdonline.Model.TypeUser;
import com.tdc.vlxdonline.Model.Users;
import com.tdc.vlxdonline.databinding.ActivityLoginBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    public static int typeUser, typeEmployee;
    ArrayList<TypeUser> dataTypeUser = new ArrayList<>();
    ArrayList<Users> dataUsers = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvSignup.setPaintFlags(binding.tvSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        setEvents();
    }

    private void setEvents() {
        KhoiTao();
        binding.btnLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Customer_HomeActivity.class);
                startActivity(intent);
            }
        });
        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.cbDisPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.cbDisPass.isChecked()) {
                    binding.edtPassLg.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    binding.edtPassLg.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        binding.spRoleLg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeUser = dataTypeUser.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void KhoiTao() {
        dataTypeUser.add(new TypeUser(0, "Chủ Cửa Hàng"));
        dataTypeUser.add(new TypeUser(1, "Khách Hàng"));
        dataTypeUser.add(new TypeUser(2, "Nhân Viên"));

        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dataTypeUser);
        binding.spRoleLg.setAdapter(adapter);
    }
}