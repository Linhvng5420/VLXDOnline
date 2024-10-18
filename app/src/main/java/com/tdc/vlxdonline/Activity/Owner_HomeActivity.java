package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ActivityOwnerHomeBinding;

public class Owner_HomeActivity extends AppCompatActivity {
    // Khai báo biến sử dụng cho View Binding để tương tác với các thành phần UI trong layout
    ActivityOwnerHomeBinding ownerHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sử dụng View Binding để lấy layout (ActivityOwnerHomeBinding) và gán vào ownerHomeBinding
        ownerHomeBinding = ActivityOwnerHomeBinding.inflate(getLayoutInflater());

        // Thiết lập nội dung hiển thị của Activity từ layout đã được binding
        setContentView(ownerHomeBinding.getRoot());

        // Thiết lập Fragment mặc định (Hiển thị màn hình mặc định ban đầu của ứng dụng)
        ReplaceFragment(new Owner_NhanVienFragment());

        // Gọi phương thức bắt sự kiện khi nhấn các nút trên thanh điều hướng (Bottom Navigation Bar)
        EventNavigationBottom();
    }

    // Phương thức bắt sự kiện cho Bottom Navigation Bar khi người dùng nhấn vào các mục
    private void EventNavigationBottom() {
        // Thiết lập listener cho sự kiện chọn item trong Bottom Navigation
        ownerHomeBinding.navOwner.setOnItemSelectedListener(item -> {
            // Lấy ID của mục được chọn
            int itemId = item.getItemId();

            // Kiểm tra ID và thay thế fragment tương ứng
            if (itemId == R.id.nav_owner_dashboard) {
                // Chuyển sang fragment Dashboard (trang chính)
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_owner_nhanvien) {
                // Chuyển sang fragment Nhân Viên
                ReplaceFragment(new Owner_NhanVienFragment());
            } else if (itemId == R.id.nav_owner_khachhang) {
                // Chuyển sang fragment Khách Hàng
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_owner_donhang) {
                // Chuyển sang fragment Đơn Hàng
                ReplaceFragment(new Fragment());
            } else if (itemId == R.id.nav_owner_kho) {
                // Chuyển sang fragment Kho (Quản lý kho hàng)
                ReplaceFragment(new Fragment());
            }

            // Trả về true để xác nhận đã xử lý sự kiện thành công
            return true;
        });
    }

    // Phương thức thay thế (replace) Fragment hiển thị trên màn hình
    private void ReplaceFragment(Fragment fragment) {
        // Lấy FragmentManager để quản lý các fragment trong Activity
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Bắt đầu một giao dịch Fragment (FragmentTransaction)
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Thay thế Fragment hiện tại bằng Fragment mới được truyền vào
        fragmentTransaction.replace(R.id.fragment_owner, fragment);

        // Xác nhận thay đổi Fragment bằng cách commit giao dịch
        fragmentTransaction.commit();
    }
}
