package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;

public class Owner_NhanVienDetailFragment extends Fragment {
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    //Khai báo DatabaseReference để kết nối với Firebase
    private DatabaseReference databaseReference;
    private NhanVien nhanVien;

    //ID nhân viên đc truyền từ Fragment trước qua
    private String selectedIDNhanVien;

    //Spinner và list item chức vụ
    private Spinner spinnerChucVu;
    private ArrayAdapter<String> chucVuAdapter;
    private ArrayList<String> chucVuList = new ArrayList<>();

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

    //TODO: THỰC HIỆN KHAI BÁO, KHỞI TẠO VÀ XỬ LÝ LOGIC TẠI ĐÂY
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Thiết lập Toolbar cho Fragment
        setupToolbar(view);

        // Khởi tạo Spinner và Adapter
        spinnerChucVu = view.findViewById(R.id.spinnerChucVu);
        chucVuAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, chucVuList);
        chucVuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChucVu.setAdapter(chucVuAdapter);

        // Lấy danh sách chức vụ từ Firebase và cập nhật vào Spinner
        layTatCaDSChucVuTuFirebase();
        setEventSpinner();

        // Lấy ID nhân viên từ Bundle rồi truy xuất thông tin nhân viên từ firebase và Hiển thị lên giao diện
        //Set Spinner Item theo Chức Vụ của nhân viên
        nhanIDNhanVienTuBundle();

        // Bắt sự kiện các Button
        
    }

    // LẤY TẤT CẢ DANH SÁCH CHỨC VỤ TỪ FIREBASE
    private void layTatCaDSChucVuTuFirebase() {
        DatabaseReference chucVuRef = FirebaseDatabase.getInstance().getReference("chucvu");

        chucVuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Xóa dữ liệu cũ trong danh sách để cập nhật lại
                chucVuList.clear();

                // Lặp qua tất cả các bản ghi chức vụ trong Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Lấy tên chức vụ từ mỗi bản ghi
                    String tenChucVu = snapshot.child("ten").getValue(String.class);
                    if (tenChucVu != null) {
                        chucVuList.add(tenChucVu);
                        Log.d("l.e", "layTatCaDSChucVuTuFirebase: " + tenChucVu);
                    }
                }

                // Cập nhật dữ liệu cho Spinner
                chucVuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                Log.d("Firebase", "Lỗi khi lấy danh sách chức vụ: " + databaseError.getMessage());
            }
        });
    }

    // NHẬN DỮ LIỆU TỪ BUNDLE, TRUY XUẤT FIREBASE VÀ HIỂN THỊ THÔNG TIN LÊN GIAO DIỆN
    private void nhanIDNhanVienTuBundle() {
        // getArguments() trả về Bundle chứa thông tin được truyền từ Fragment trước
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
                            nhanvienDetailBinding.etSDT.setText(nhanVien.getSdt());
                            nhanvienDetailBinding.etEmail.setText(nhanVien.getEmailnv());
                            nhanvienDetailBinding.etCCCD.setText(nhanVien.getCccd());

                            // Lấy tên chức vụ và gán nó vào Spinner
                            truyXuatChucVuTuFireBase(nhanVien.getChucvu());

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
    private void truyXuatChucVuTuFireBase(String chucVuId) {
        DatabaseReference chucVuRef = FirebaseDatabase.getInstance().getReference("chucvu").child(chucVuId);

        // Lấy dữ liệu tên chức vụ từ Firebase
        chucVuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String tenChucVu = dataSnapshot.child("ten").getValue(String.class);
                    nhanvienDetailBinding.etChucVu.setText(tenChucVu);
                    nhanVien.setChucvu(tenChucVu);
                    Log.d("l.e", "truyXuatChucVuTuFireBase ID = " + chucVuId + ", Tên Chức Vụ = " + tenChucVu + ", nhanVien.getChucvu = " + nhanVien.getChucvu());

                    // 2. gán item spinner theo chức vụ nhân viên
                    // Tìm vị trí của giá trị 'chucVu' trong danh sách Spinner
                    int position = chucVuAdapter.getPosition(tenChucVu);
                    if (position != -1) {  // Nếu tìm thấy chucVu trong danh sách
                        spinnerChucVu.setSelection(position); // Đặt item tương ứng được chọn
                    } else {
                        nhanvienDetailBinding.etChucVu.setText("Không tìm thấy chức vụ: " + tenChucVu);
                        spinnerChucVu.setSelection(0); //nếu ko thấy thì để mặc định là 0 - kho khi sửa.
                        Log.d("Spinner", "Không tìm thấy chức vụ: " + tenChucVu + " trong danh sách Spinner.");
                    }
                } else {
                    nhanvienDetailBinding.etChucVu.setText("Chức Vụ \"" + chucVuId + "\" Không Tồn Tại Trong Hệ Thống");
                    Log.d("l.e", "Không tìm thấy chức vụ với ID: " + chucVuId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nhanvienDetailBinding.etChucVu.setText("N/A"); // Xử lý lỗi nếu có
            }
        });
    }

    // BẮT SỰ KIỆN SPINNER
    private void setEventSpinner() {
        spinnerChucVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy tên chức vụ được chọn
                String selectedChucVu = chucVuList.get(position);
                // Xử lý sau khi người dùng chọn chức vụ
                Log.d("Spinner", "Chức vụ được chọn: " + selectedChucVu);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có gì được chọn
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

