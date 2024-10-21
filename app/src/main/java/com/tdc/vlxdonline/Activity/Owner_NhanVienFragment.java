package com.tdc.vlxdonline.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdc.vlxdonline.Adapter.NhanVienAdapter;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Owner_NhanVienFragment extends Fragment {

    // Khai báo đối tượng binding để liên kết với layout của Fragment
    private FragmentOwnerNhanvienBinding ownerNhanvienBinding;

    // Firebase: Khai báo DatabaseReference
    private DatabaseReference databaseReferenceNhanVien;
    private DatabaseReference databaseReferenceChucVu;

    // Adapter để hiển thị danh sách nhân viên
    private NhanVienAdapter nhanVienAdapter;

    private Map<String, String> chucVuMap = new HashMap<>();  // Khai báo Map để lưu chức vụ

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Khởi tạo View Binding cho Fragment và liên kết với layout fragment_owner_nhanvien.xml
        ownerNhanvienBinding = FragmentOwnerNhanvienBinding.inflate(inflater, container, false);

        // Thiết lập OnTouchListener để ẩn bàn phím khi chạm ra ngoài EditText
        ownerNhanvienBinding.getRoot().setOnTouchListener((v, event) -> {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;     // Trả về false để tiếp tục xử lý các sự kiện chạm khác nếu có
        });

        // Trả về đối tượng View được tạo từ binding, đây là root view của Fragment
        return ownerNhanvienBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Firebase: Khởi tạo databaseReference
        databaseReferenceNhanVien = FirebaseDatabase.getInstance().getReference("NhanVien");
        databaseReferenceChucVu = FirebaseDatabase.getInstance().getReference("ChucVu");

        // Thiết lập layout cho RecyclerView, sử dụng LinearLayoutManager để hiển thị danh sách theo chiều dọc
        ownerNhanvienBinding.ownerRcvNhanVien.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo danh sách nhân viên và adapter
        List<NhanVien> nhanVienList = new ArrayList<>();
        nhanVienAdapter = new NhanVienAdapter(nhanVienList);
        ownerNhanvienBinding.ownerRcvNhanVien.setAdapter(nhanVienAdapter);

        // Firebase: Gọi phương thức để lấy dữ liệu từ Firebase
        getNhanVienData();
        getChucVuData();

        // Sự kiện khi nhấn nút Thêm Nhân Viên
        SuKienThemNhanVien();

        // Sự kiện khi nhấn vào một item trong danh sách nhân viên
        SuKienNhanItemNhanVien();

    }

    // TODO: Them Nhan Vien
    private void SuKienThemNhanVien() {
        ownerNhanvienBinding.btnThemNhanVien.setOnClickListener(v -> {
            // Tạo một instance của Owner_NhanVienDetailFragment
            Owner_NhanVienDetailFragment detailFragment = new Owner_NhanVienDetailFragment();

            // Đưa trạng thái là "Thêm Nhân Viên" vào Bundle
            Bundle bundle = new Bundle();
            bundle.putBoolean("isAddingNewEmployee", true); // Gán trạng thái thêm mới
            detailFragment.setArguments(bundle);

            // Chuyển tới Fragment chi tiết thêm nhân viên
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_owner, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }


    private void SuKienNhanItemNhanVien() {
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
        databaseReferenceNhanVien.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nhanVienAdapter.getNhanVienList().clear();  // Xóa danh sách cũ

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        NhanVien nhanVien = snapshot.getValue(NhanVien.class);
                        if (nhanVien != null) {
                            // Lấy document ID (key) từ Firebase snapshot
                            String nhanVienId = snapshot.getKey();
                            nhanVien.setID(nhanVienId);  // Gán ID vào đối tượng NhanVien

                            // Thay thế mã chức vụ bằng tên chức vụ
                            String tenChucVu = chucVuMap.get(nhanVien.getChucVu());
                            nhanVien.setChucVu(tenChucVu != null ? tenChucVu : "N/A");  // Nếu không tìm thấy, đặt là "N/A"

                            nhanVienAdapter.getNhanVienList().add(nhanVien);  // Thêm nhân viên vào danh sách
                        }
                    } catch (DatabaseException e) {
                        Log.e("FirebaseError", "Lỗi FireBase: " + e.getMessage());
                    }
                }
                nhanVienAdapter.notifyDataSetChanged();  // Cập nhật adapter
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });
    }


    private void getChucVuData() {
        databaseReferenceChucVu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chucVuMap.clear();  // Xóa danh sách chức vụ cũ trước khi thêm mới
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String maChucVu = snapshot.getKey();  // Lấy mã chức vụ
                    String tenChucVu = snapshot.child("Ten").getValue(String.class);  // Lấy tên chức vụ
                    if (maChucVu != null && tenChucVu != null) {
                        chucVuMap.put(maChucVu, tenChucVu);  // Thêm vào Map
                    }
                }
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


