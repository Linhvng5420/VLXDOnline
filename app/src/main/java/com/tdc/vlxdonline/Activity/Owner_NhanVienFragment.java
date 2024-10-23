package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.util.Log;
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
    // Adapter để hiển thị danh sách nhân viên
    private NhanVienAdapter nhanVienAdapter;

    // FIREBASE: Khai báo DatabaseReference
    private DatabaseReference databaseReference;

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

        //RecycleView: Thiết lập layout cho RecyclerView, sử dụng LinearLayoutManager để hiển thị danh sách theo chiều dọc
        ownerNhanvienBinding.ownerRcvNhanVien.setLayoutManager(new LinearLayoutManager(getContext()));
        // Khởi tạo danh sách nhân viên trống và adapter
        List<NhanVien> nhanVienList = new ArrayList<>();
        nhanVienAdapter = new NhanVienAdapter(nhanVienList);
        ownerNhanvienBinding.ownerRcvNhanVien.setAdapter(nhanVienAdapter);

        // Firebase: lấy dữ liệu từ Firebase
        getNhanVienData();

        //nhấn vào recycleview nhân viên
        nhanVaoItemNhanVien();
    }

    private void getNhanVienData() {
        // Firebase: Khởi tạo databaseReference và lấy dữ liệu từ Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("nhanvien");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Xóa danh sách cũ trước khi thêm dữ liệu mới
                nhanVienAdapter.getNhanVienList().clear();

                // Lặp qua tất cả các DataSnapshot con để lấy thông tin nhân viên
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Lấy đối tượng NhanVien từ snapshot
                    NhanVien nhanVien = snapshot.getValue(NhanVien.class);
                    if (nhanVien != null) {
                        // Set idnv là document ID
                        nhanVien.setIdnv(snapshot.getKey());

                        // Thêm nhân viên vào danh sách
                        nhanVienAdapter.getNhanVienList().add(nhanVien);
//                        Log.d("l.e", " " + nhanVien.toString());
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

    // Thiết lập sự kiện khi nhấn vào một item trong danh sách nhân viên
    private void nhanVaoItemNhanVien() {
        nhanVienAdapter.setOnItemClickListener(nhanVien -> {
            // Tạo Bundle để truyền thông tin nhân viên được chọn qua Fragment Detail
            Bundle bundleIDNhanVien = new Bundle();
            bundleIDNhanVien.putSerializable("selectedIDNhanVien", nhanVien.getIdnv()); // Đưa dữ liệu ID nhân viên vào Bundle

            // Tạo một instance của Owner_NhanVienDetailFragment
            Owner_NhanVienDetailFragment nhanVienDetailFragment = new Owner_NhanVienDetailFragment();

            // Gán Bundle (chứa thông tin id nhân viên) vào Fragment chi tiết
            nhanVienDetailFragment.setArguments(bundleIDNhanVien);

            // Thực hiện chuyển đổi sang Fragment chi tiết, thay thế Fragment hiện tại
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_owner, nhanVienDetailFragment) // Thay thế fragment_owner hiện tại bằng fragment chi tiết
                    .addToBackStack(null) // Cho phép quay lại màn hình trước khi nhấn nút Back
                    .commit(); // Thực hiện chuyển đổi
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Giải phóng tài nguyên của binding để tránh việc rò rỉ bộ nhớ khi Fragment bị hủy
        ownerNhanvienBinding = null;
    }
}


