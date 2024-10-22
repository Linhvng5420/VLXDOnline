package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

public class Owner_NhanVienDetailFragment extends Fragment {
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    //Khai báo DatabaseReference để kết nối với Firebase
    private DatabaseReference databaseReference;
    private NhanVien nhanVien;

    //ID nhân viên đc truyền từ Fragment trước qua
    private String selectedIDNhanVien;

    //Spinner
    private Spinner spinnerChucVu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khởi tạo databaseReference với tên nhánh cần truy cập trong Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("nhanVien");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Gán binding cho layout fragment_owner_nhanvien_detail.xml bằng cách sử dụng phương thức inflate()
        nhanvienDetailBinding = FragmentOwnerNhanvienDetailBinding.inflate(inflater, container, false);
        return nhanvienDetailBinding.getRoot(); // Trả về toàn bộ giao diện của fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Thiết lập Toolbar cho Fragment
        setupToolbar(view);

        //Lấy ID nhân viên từ Bundle rồi truy xuất thông tin nhân viên từ firebase
        nhanIDNhanVienTuBundle();
    }

    // NHẬN DỮ LIỆU TỪ BUNDLE, TRUY XUẤT FIREBASE VÀ HIỂN THỊ THÔNG TIN
    private void nhanIDNhanVienTuBundle() {
        //getArguments() trả về Bundle chứa thông tin được truyền từ Fragment trước
        if (getArguments() != null) // Kiểm tra xem Bundle có tồn tại hay không
        {
            // Lấy thông tin nhân viên từ Bundle
            selectedIDNhanVien = getArguments().getSerializable("selectedIDNhanVien").toString();

            // Hiển thị thông tin ID nhân viên lên giao diện
            Toast.makeText(getContext(), "ID Nhân Viên: " + selectedIDNhanVien, Toast.LENGTH_SHORT).show();
            Log.d("l.e", "nhanIDNhanVienTuBundle: " + selectedIDNhanVien.toString());
            nhanvienDetailBinding.tvIDNhanVien.setText("ID: " + selectedIDNhanVien.toUpperCase());

            // Lấy thông tin nhân viên từ firebase thông qua ID
            databaseReference = FirebaseDatabase.getInstance().getReference("nhanvien");

            databaseReference.child(selectedIDNhanVien).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Lấy thông tin nhân viên từ firebase và ánh xạ vào đối tượng NhanVien
                        nhanVien = dataSnapshot.getValue(NhanVien.class);
                        nhanVien.setIdnv(dataSnapshot.getKey());
                        Log.d("l.e", "Firebase: " + nhanVien.toString());

                        if (nhanVien != null) {
                            nhanvienDetailBinding.etTenNhanVien.setText(nhanVien.getTennv());
                            TruyXuatChucVuTuFireBase(nhanVien.getChucvu());
                            nhanvienDetailBinding.etSDT.setText(nhanVien.getSdt());
                            nhanvienDetailBinding.etEmail.setText(nhanVien.getEmailnv());
                            nhanvienDetailBinding.etCCCD.setText(nhanVien.getCccd());
                        } else {
                            Log.d("l.e", "Nhân viên không tồn tại trong cơ sở dữ liệu.");
                        }
                    } else {
                        Log.d("l.e", "Không tìm thấy nhân viên với ID: " + selectedIDNhanVien);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("l.e", "Lỗi truy xuất thông tin nhân viên từ firebase: " + databaseError.getMessage());
                }
            });

        } else {
            Log.d("l.e", "nhanIDNhanVienTuBundle: Lỗi truyền bundle từ fragment qua Detail");
        }
    }
    // TRUY XUẤT CHỨC VỤ CỦA NHÂN VIÊN TỪ FIREBASE BẰNG ID
    private void TruyXuatChucVuTuFireBase(String chucVuId) {
        DatabaseReference chucVuRef = FirebaseDatabase.getInstance().getReference("chucvu").child(chucVuId);

        // Lấy dữ liệu tên chức vụ từ Firebase
        chucVuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String tenChucVu = dataSnapshot.child("ten").getValue(String.class);
                    nhanvienDetailBinding.etChucVu.setText(tenChucVu != null ? tenChucVu : "N/A"); // Gán tên chức vụ vào TextView
                    Log.d("l.e", "chức vụ với ID: " + chucVuId + "CV: " + tenChucVu);
                } else {
                    nhanvienDetailBinding.etChucVu.setText("N/A"); // Gán tên chức vụ vào TextView
                    Log.d("l.e", "Không tìm thấy chức vụ với ID: " + chucVuId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nhanvienDetailBinding.etChucVu.setText("N/A"); // Xử lý lỗi nếu có
            }
        });
    }


    // CUỐI: THIẾT LẬP TOOLBAR VÀ ĐIỀU HƯỚNG
    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý khi nhấn nút quay về trên Toolbar
        toolbar.setNavigationOnClickListener(v -> getParentFragmentManager().popBackStack());
    }
}

