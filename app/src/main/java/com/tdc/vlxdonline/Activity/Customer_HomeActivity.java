package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ActivityCustomerHomeBinding;

public class Customer_HomeActivity extends AppCompatActivity {

    ActivityCustomerHomeBinding customerHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerHomeBinding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(customerHomeBinding.getRoot());

        ReplaceFragment(new Customer_Home_Fragment());
        //3. Bắt sự kiện
        EventNavigationBottom();
    }

    // Bắt sự kiện nhấn Navbar Bottom
    private void EventNavigationBottom() {
        customerHomeBinding.navCustomer.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_customer_sanpham) {
                ReplaceFragment(new Customer_Home_Fragment());
            } else if (itemId == R.id.nav_customer_giohang) {
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_customer_donhang) {
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_customer_taikhoan) {
                ReplaceFragment(new Fragment());
            }

            return true;
        });
    }

    private void ReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(customerHomeBinding.frmCustomer.getId(), fragment);
        fragmentTransaction.commit();
    }
}