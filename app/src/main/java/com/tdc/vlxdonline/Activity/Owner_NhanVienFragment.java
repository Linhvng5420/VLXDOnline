package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdc.vlxdonline.Adapter.NhanVienAdapter;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienBinding;

import java.util.ArrayList;
import java.util.List;

public class Owner_NhanVienFragment extends Fragment {

    // Khai báo đối tượng binding để liên kết với layout của Fragment
    private FragmentOwnerNhanvienBinding ownerNhanvienBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Khởi tạo View Binding cho Fragment và liên kết với layout fragment_owner_nhanvien.xml
        ownerNhanvienBinding = FragmentOwnerNhanvienBinding.inflate(inflater, container, false);

        // Trả về đối tượng View được tạo từ binding, đây là root view của Fragment
        return ownerNhanvienBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Thiết lập layout cho RecyclerView, sử dụng LinearLayoutManager để hiển thị danh sách theo chiều dọc
        ownerNhanvienBinding.ownerRcvNhanVien.setLayoutManager(new LinearLayoutManager(getContext()));

        // Gọi phương thức generateDummyData() để tạo và nhận danh sách dữ liệu mẫu (50 nhân viên)
        // Khai báo danh sách nhân viên sẽ được sử dụng để đổ dữ liệu vào RecyclerView
        List<NhanVien> nhanVienList = generateDummyData();

        // Khởi tạo Adapter với danh sách dữ liệu mẫu và gán nó vào RecyclerView
        // Khai báo Adapter để hiển thị danh sách nhân viên trong RecyclerView
        NhanVienAdapter nhanVienAdapter = new NhanVienAdapter(nhanVienList);
        ownerNhanvienBinding.ownerRcvNhanVien.setAdapter(nhanVienAdapter);

        // Thiết lập sự kiện khi nhấn vào một item trong danh sách nhân viên
        nhanVienAdapter.setOnItemClickListener(nhanVien -> {
            // Tạo Bundle để truyền thông tin nhân viên được chọn qua Fragment chi tiết
            Bundle bundle = new Bundle();
            bundle.putSerializable("selectedNhanVien", nhanVien); // Đưa dữ liệu nhân viên vào Bundle

            // Tạo một instance của Owner_NhanVienDetailFragment
            Owner_NhanVienDetailFragment detailFragment = new Owner_NhanVienDetailFragment();

            // Gán Bundle (chứa thông tin nhân viên) vào Fragment chi tiết
            detailFragment.setArguments(bundle);

            // Thực hiện chuyển đổi sang Fragment chi tiết, thay thế Fragment hiện tại
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_owner, detailFragment) // Thay thế fragment_owner bằng fragment chi tiết
                    .addToBackStack(null) // Cho phép quay lại màn hình trước khi nhấn nút Back
                    .commit(); // Áp dụng transaction
        });
    }

    // Phương thức tạo dữ liệu mẫu gồm 50 nhân viên
    private List<NhanVien> generateDummyData() {
        // Khởi tạo danh sách nhân viên rỗng
        List<NhanVien> nhanVienList = new ArrayList<>();

        // Vòng lặp từ 1 đến 50 để tạo 50 nhân viên
        for (int i = 1; i <= 50; i++) {
            // ChucVu chỉ nhận giá trị 0 (Kho) hoặc 1 (Giao Hàng), sử dụng i % 2 để luân phiên giữa 0 và 1
            int chucVu = i % 2;

            // Thêm nhân viên vào danh sách với thông tin cơ bản (ID, IDChu, Tên, Chức vụ, Số điện thoại, Email)
            nhanVienList.add(new NhanVien(i, 1, "Nhan Vien " + i, chucVu, "0123456789" + i, "nhanvien" + i + "@example.com"));
        }

        // Trả về danh sách nhân viên sau khi đã tạo đủ 50 phần tử
        return nhanVienList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Giải phóng tài nguyên của binding để tránh việc rò rỉ bộ nhớ khi Fragment bị hủy
        ownerNhanvienBinding = null;
    }
}

