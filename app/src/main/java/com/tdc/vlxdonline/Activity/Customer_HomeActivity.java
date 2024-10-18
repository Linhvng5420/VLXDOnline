package com.tdc.vlxdonline.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ActivityCustomerHomeBinding;

public class Customer_HomeActivity extends AppCompatActivity {

    ActivityCustomerHomeBinding customerHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerHomeBinding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(customerHomeBinding.getRoot());

        ReplaceFragment(new CustomerHomeFragment());
        //3. Bắt sự kiện
        EventNavigationBottom();
    }

    // Bắt sự kiện nhấn Navbar Bottom
    private void EventNavigationBottom() {
        customerHomeBinding.navCustomer.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_customer_sanpham) {
                ReplaceFragment(new CustomerHomeFragment());
            } else if (itemId == R.id.nav_customer_giohang) {
                ReplaceFragment(new CartFragment());
            } else if (itemId == R.id.nav_customer_donhang) {
                ReplaceFragment(new DonHangFragment());
            } else if (itemId == R.id.nav_customer_taikhoan) {
                ReplaceFragment(new AccountCustomerFragment());
            }

            return true;
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {}

    private void ReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(customerHomeBinding.frmCustomer.getId(), fragment);
        fragmentTransaction.commit();
    }
}