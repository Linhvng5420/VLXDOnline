package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ActivityOwnerHomeBinding;

public class Owner_HomeActivity extends AppCompatActivity {
    //1. Declare variables
    ActivityOwnerHomeBinding ownerHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownerHomeBinding = ActivityOwnerHomeBinding.inflate(getLayoutInflater());
        setContentView(ownerHomeBinding.getRoot());

        //2. Set Fragment mặc định (Màn hình mặc định)


        //3. Bắt sự kiện
        EventNavigationBottom();
    }

    // Bắt sự kiện nhấn Navbar Bottom
    private void EventNavigationBottom() {
        ownerHomeBinding.navOwner.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_owner_dashboard) {
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_owner_nhanvien) {
                ReplaceFragment(new Owner_StaffFragment());
            } else if (itemId == R.id.nav_owner_khachhang) {
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_owner_donhang) {
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_owner_kho) {
                ReplaceFragment(new Fragment());
            }

            return true;
        });
    }

    private void ReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_owner, fragment);
        fragmentTransaction.commit();
    }
}