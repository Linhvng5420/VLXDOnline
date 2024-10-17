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
            if (item.getItemId() == R.id.nav_home_nhanvien) {
                ReplaceFragment(new Owner_StaffFragment());
            } else if (item.getItemId() == R.id.nav_home_dashboard) {
                ReplaceFragment(new Fragment());
            } else if (item.getItemId() == R.id.nav_home_donhang) {
                
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