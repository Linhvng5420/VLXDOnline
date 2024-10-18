package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ActivityCustomerHomeBinding;

public class Customer_HomeActivity extends AppCompatActivity {
    
    ActivityCustomerHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setDefaultValues();
        setNavbarEvents();
    }

    private void setDefaultValues() {
        ReplaceFragment(new Customer_Home_Fragment());
    }

    private void setNavbarEvents() {
        binding.navCustomer.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                
                if (item.getItemId() == R.id.nav_customer_sanpham) {
                    ReplaceFragment(new Customer_Home_Fragment());
                } else if (item.getItemId() == R.id.nav_customer_giohang) {
                    ReplaceFragment(new Customer_Home_Fragment());
                } else if (item.getItemId() == R.id.nav_customer_donhang) {
                    ReplaceFragment(new Customer_Home_Fragment());
                } else if (item.getItemId() == R.id.nav_customer_taikhoan) {
                    ReplaceFragment(new Customer_Home_Fragment());
                }

                return true;
            }
        });
    }
    
    private void ReplaceFragment(Fragment fragment){
        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction fr = f.beginTransaction();
        fr.replace(binding.frmCustomer.getId(), fragment);
        fr.commit();
    }
}