package com.tdc.vlxdonline.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tdc.vlxdonline.Model.Categorys;
import com.tdc.vlxdonline.Model.DonVi;
import com.tdc.vlxdonline.Model.SendMail;
import com.tdc.vlxdonline.Model.TypeUser;
import com.tdc.vlxdonline.Model.Users;
import com.tdc.vlxdonline.databinding.ActivityLoginBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    public static int typeUser, typeEmployee = -1;
    String idUser = "";
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
        // Login
        binding.btnLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkLg = Login();
                if (checkLg) {
                    Class c = null;
                    if (typeUser == 0) {
                        c = Owner_HomeActivity.class;
                    } else if (typeUser == 1) {
                        c = Customer_HomeActivity.class;
                    } else if (typeEmployee == 0) {
                        c = Warehouse_HomeActivity.class;
                    } else if (typeEmployee == 1) {
                        c = Shipper_HomeActivity.class;
                    }
                    Intent intent = new Intent(LoginActivity.this, c);
                    intent.putExtra("email", idUser);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Sai Thông Tin Đăng Nhập!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Sign Up
        binding.tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // Dis pass
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
        // Choose role
        binding.spRoleLg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeUser = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean Login(){
        Users user = null;
        for (int i = 0; i < dataUsers.size(); i++) {
            if (dataUsers.get(i).getEmail().equals(binding.edtEmailLg.getText().toString())) {
                user = dataUsers.get(i);
            }
        }
        if (user != null) {
            if (Integer.parseInt(user.getType_user()) != typeUser) {
                return false;
            }else if (user.getEmail().equals(binding.edtEmailLg.getText().toString())
                    && user.getPass().equals(binding.edtPassLg.getText().toString())) {
                idUser = user.getEmail();
                if (typeUser == 2) {
                    typeEmployee = 0;
                }
                return true;
            }
        }
        return false;
    }

    private void KhoiTao() {
        dataTypeUser.add(new TypeUser("0", "Chủ Cửa Hàng"));
        dataTypeUser.add(new TypeUser("1", "Khách Hàng"));
        dataTypeUser.add(new TypeUser("2", "Nhân Viên"));

        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dataTypeUser);
        binding.spRoleLg.setAdapter(adapter);

        dataUsers.add(new Users("abc", "123", "0"));
        dataUsers.add(new Users("bcd", "234", "1"));
        dataUsers.add(new Users("def", "345", "2"));
    }
}