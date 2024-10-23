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
import androidx.appcompat.app.AlertDialog;
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
        setupEditButton();
        //setupSaveButton();
        setupDeleteButton();
        setupCancelButton();
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

    private void truyXuatChucVuTuFireBase(String chucVuId) {
        if (chucVuId == null || chucVuId.isEmpty()) {
            // Nếu chucVuId null hoặc rỗng, hiển thị thông báo lỗi
            nhanvienDetailBinding.etChucVu.setText("Lỗi Database, Mất Field Chức Vụ");
            Log.d("l.e", "chucVuId null hoặc rỗng.");
            return; // Dừng hàm tại đây để tránh gọi Firebase với chucVuId null
        }

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

                    // Gán item spinner theo chức vụ nhân viên
                    int position = chucVuAdapter.getPosition(tenChucVu);
                    if (position != -1) {
                        spinnerChucVu.setSelection(position); // Đặt item tương ứng được chọn
                    } else {
                        nhanvienDetailBinding.etChucVu.setText("Không tìm thấy chức vụ: " + tenChucVu);
                        spinnerChucVu.setSelection(0); // Nếu không thấy thì để mặc định là 0
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

    // THIẾT LẬP SỰ KIỆN CHO CÁC NÚT
    private void setupEditButton() {
        nhanvienDetailBinding.btnChinhSua.setOnClickListener(v -> {
            // Kích hoạt chỉnh sửa cho các trường thông tin
            nhanvienDetailBinding.etTenNhanVien.setEnabled(true);
            nhanvienDetailBinding.etSDT.setEnabled(true);
            nhanvienDetailBinding.etEmail.setEnabled(true);
            nhanvienDetailBinding.etCCCD.setEnabled(true);

            // Hiển thị Spinner chọn chức vụ
            nhanvienDetailBinding.tilChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.etChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.spinnerChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.tvChucVu.setVisibility(View.VISIBLE);

            // Hiển thị nút Lưu Lại, Xóa, Hủy. Ẩn nút Chỉnh Sửa
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.btnXoa.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.btnHuy.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.INVISIBLE);
        });
    }
    private void setupCancelButton() {
        nhanvienDetailBinding.btnHuy.setOnClickListener(v -> {
            // Tạo hộp thoại xác nhận
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác Nhận")
                    .setMessage("Bạn có chắc chắn muốn hủy thay đổi không?")
                    .setPositiveButton("Hủy", (dialog, which) -> {

                        // Vô hiệu hóa các trường chỉnh sửa sau khi hủy
                        nhanvienDetailBinding.etTenNhanVien.setEnabled(false);
                        nhanvienDetailBinding.etSDT.setEnabled(false);
                        nhanvienDetailBinding.etEmail.setEnabled(false);
                        nhanvienDetailBinding.etCCCD.setEnabled(false);

                        // Ẩn Spinner và hiển thị TextView cho chức vụ
                        nhanvienDetailBinding.tilChucVu.setVisibility(View.VISIBLE);
                        nhanvienDetailBinding.etChucVu.setVisibility(View.VISIBLE);
                        nhanvienDetailBinding.spinnerChucVu.setVisibility(View.INVISIBLE);
                        nhanvienDetailBinding.tvChucVu.setVisibility(View.INVISIBLE);

                        // Ẩn nút Lưu Lại, Xóa, Hủy và Hiển thị nút Sửa sau khi Hủy
                        nhanvienDetailBinding.btnLuuLai.setVisibility(View.INVISIBLE);
                        nhanvienDetailBinding.btnHuy.setVisibility(View.VISIBLE);
                        nhanvienDetailBinding.btnXoa.setVisibility(View.INVISIBLE);
                        nhanvienDetailBinding.btnHuy.setVisibility(View.INVISIBLE);
                        nhanvienDetailBinding.btnChinhSua.setVisibility(View.VISIBLE);
                    })
                    .setNegativeButton("Không Hủy", null) // Hiển thị hộp thoại
                    .show();
        });
    }
    private void setupDeleteButton() {
        nhanvienDetailBinding.btnXoa.setOnClickListener(view -> {
            // Tạo hộp thoại xác nhận
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác Nhận")
                    .setMessage("Bạn có chắc chắn muốn xóa nhân viên không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        // Xóa nhân viên khỏi Firebase có document = selectedIDNhanVien
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("nhanvien").child(selectedIDNhanVien);

                        databaseReference.removeValue()
                                .addOnSuccessListener(aVoid -> {
                                    // Hiển thị thông báo đã xóa thành công
                                    Toast.makeText(getContext(), "Đã xóa nhân viên!", Toast.LENGTH_SHORT).show();

                                    // Quay lại màn hình quản lý nhân viên sau khi xóa
                                    getParentFragmentManager().popBackStack();
                                })
                                .addOnFailureListener(e -> {
                                    // Hiển thị thông báo lỗi nếu không xóa được
                                    Toast.makeText(getContext(), "Lỗi khi xóa nhân viên: ", Toast.LENGTH_SHORT).show();
                                });
                    })
                    .setNegativeButton("Không", null) // Không làm gì khi người dùng nhấn "Không"
                    .show();
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

