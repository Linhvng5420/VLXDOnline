package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdc.vlxdonline.Adapter.NhanVienAdapter;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienBinding;

import java.util.ArrayList;
import java.util.List;

public class Owner_NhanVienFragment extends Fragment {

    // Khai báo đối tượng binding để liên kết với layout của Fragment
    private FragmentOwnerNhanvienBinding ownerNhanvienBinding;

    // Firebase: Khai báo DatabaseReference
    private DatabaseReference databaseReference;

    // Adapter để hiển thị danh sách nhân viên
    private NhanVienAdapter nhanVienAdapter;

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

        // Firebase: Khởi tạo databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("nhanVien"); // Đảm bảo rằng "nhanVien" là tên đúng trong Firebase Database

        // Thiết lập layout cho RecyclerView, sử dụng LinearLayoutManager để hiển thị danh sách theo chiều dọc
        ownerNhanvienBinding.ownerRcvNhanVien.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo danh sách nhân viên và adapter
        List<NhanVien> nhanVienList = new ArrayList<>();
        nhanVienAdapter = new NhanVienAdapter(nhanVienList);
        ownerNhanvienBinding.ownerRcvNhanVien.setAdapter(nhanVienAdapter);

        // Firebase: Gọi phương thức để lấy dữ liệu từ Firebase
        getNhanVienData();

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

    private void getNhanVienData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Xóa danh sách cũ trước khi thêm dữ liệu mới
                nhanVienAdapter.getNhanVienList().clear();

                // Duyệt qua các snapshot và thêm dữ liệu nhân viên vào danh sách
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NhanVien nhanVien = snapshot.getValue(NhanVien.class);
                    if (nhanVien != null) {
                        nhanVienAdapter.getNhanVienList().add(nhanVien);
                    }
                }
                // Thông báo cho adapter cập nhật dữ liệu
                nhanVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Giải phóng tài nguyên của binding để tránh việc rò rỉ bộ nhớ khi Fragment bị hủy
        ownerNhanvienBinding = null;
    }
}


