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
import com.tdc.vlxdonline.Model.ChucVu;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanVienAddBinding;

import java.util.ArrayList;
import java.util.List;

public class Owner_NhanVienAddFragment extends Fragment {
    FragmentOwnerNhanVienAddBinding addBinding;

    //Khai báo DatabaseReference để kết nối với Firebase
    private DatabaseReference databaseReference;
    private NhanVien nhanVien;

    //Danh sách chức vụ từ Firebase
    private List<ChucVu> listChucVuFireBase = new ArrayList<>();

    //Spinner và list item chức vụ
    private Spinner spinnerChucVu;
    private ArrayAdapter<String> chucVuAdapter;
    private ArrayList<String> listChucVuSpinner = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addBinding = FragmentOwnerNhanVienAddBinding.inflate(inflater, container, false);
        return addBinding.getRoot();
    }

    //TODO: THỰC HIỆN KHAI BÁO, KHỞI TẠO VÀ XỬ LÝ LOGIC TẠI ĐÂY
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Thiết lập Toolbar cho Fragment
        setupToolbar(view);

        // Khởi tạo đối tượng nhân viên dùng chung và duy nhất trong toàn bộ Fragment
        nhanVien = new NhanVien();

        // Khởi tạo Spinner và Adapter
        chucVuAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listChucVuSpinner);
        chucVuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addBinding.spinnerChucVu.setAdapter(chucVuAdapter);

        // Lấy danh sách chức vụ từ Firebase và cập nhật vào Spinner
        layTatCaDSChucVuTuFirebase();
        setEventSpinner();

        // Bắt sự kiện các Button
        setupSaveButton();
    }

    // LẤY TẤT CẢ DANH SÁCH CHỨC VỤ TỪ FIREBASE THEO THỜI GIAN THỰC
    private void layTatCaDSChucVuTuFirebase() {
        DatabaseReference chucVuRef = FirebaseDatabase.getInstance().getReference("chucvu");

        chucVuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Xóa danh sách tên chức vụ trước khi thêm dữ liệu mới
                listChucVuSpinner.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChucVu chucVu = new ChucVu();
                    String idChucVu = snapshot.getKey();
                    String tenChucVu = snapshot.child("ten").getValue(String.class);

                    chucVu.setIdChucVu(idChucVu);
                    chucVu.setTenChucVu(tenChucVu);
                    listChucVuFireBase.add(chucVu);

                    // thêm vào danh sách spinner
                    String displayText = tenChucVu;
                    listChucVuSpinner.add(displayText);

                    // Thông báo cho adapter cập nhật dữ liệu cho Spinner
                    chucVuAdapter.notifyDataSetChanged();

                    Log.d("l.e", "layTatCaDSChucVuTuFirebase: " + chucVu.toString());
                    Log.d("l.e", "layTatCaDSChucVuTuFirebase: Display: " + chucVu.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("l.e", "Lỗi khi lấy dữ liệu chức vụ", databaseError.toException());
            }
        });
    }

    // BẮT SỰ KIỆN SPINNER
    private void setEventSpinner() {
        addBinding.spinnerChucVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy tên chức vụ được chọn
                String selectedChucVu = listChucVuSpinner.get(position);
                // Xử lý sau khi người dùng chọn chức vụ
                Log.d("Spinner", "Chức vụ được chọn: " + selectedChucVu);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có gì được chọn
            }
        });
    }

    private void setupSaveButton() {
        addBinding.btnThemNhanVien.setOnClickListener(v -> {
            // Bắt điều kiện dữ liệu đầu vào
            if (!batDieuKienDuLieuDauVao()) return;

            // Hộp thoại xác nhận
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có muốn thêm nhân viên này?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        // Lưu giá trị Chức vụ từ Spinner
                        String tenChucVuMoi = addBinding.spinnerChucVu.getSelectedItem().toString();
                        Log.d("l.e", "setupSaveButton: tenChucVuMoi Spinner = " + tenChucVuMoi);

                        docIDChucVuBangTen(tenChucVuMoi);

                        nhanVien.setTennv(addBinding.etTenNhanVien.getText().toString());
                        nhanVien.setSdt(addBinding.etSDT.getText().toString());
                        nhanVien.setEmailnv(addBinding.etEmail.getText().toString());
                        nhanVien.setEmailchu("Test@m.c");
                        nhanVien.setCccd(addBinding.etCCCD.getText().toString());

                        // Tạo mã nhân viên mới bằng timestamp
                        long timestamp = System.currentTimeMillis();
                        String maNhanVien = "nv" + timestamp;

                        // Lưu nhân viên vào Firebase với key
                        databaseReference = FirebaseDatabase.getInstance().getReference("nhanvien");

                        databaseReference.child(maNhanVien).setValue(nhanVien) //Thêm nv mới vào firebase với Key(document)=idnv, các value còn lại tự thêm.
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(getContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(getContext(), "Thêm nhân viên thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });

                        // Thêm nhân viên vào Firebase với mã ngẫu nhiên (Dài và xấu)
//                        databaseReference.push().setValue(nhanVien);
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });
    }

    // TÌM VÀ ĐỌC ID CHỨC VỤ TRONG listChucVuFireBase BẰNG TÊN CHỨC VỤ TRUYỀN VÀO
    private void docIDChucVuBangTen(String tenChucVu) {
        if (tenChucVu == null || tenChucVu.isEmpty()) {
            Log.d("l.e", "docIDChucVuBangTen: Item Spinner.");
            return;
        }
        Log.d("l.e", "listChucVuFireBase: " + listChucVuFireBase.toString());

        // Tìm ID chức vụ có tên trùng khớp trong danh sách
        if (listChucVuFireBase != null) {
            String idChucVu = null;
            for (ChucVu chucVu : listChucVuFireBase) {
                if (chucVu.getTenChucVu().equals(tenChucVu)) {
                    idChucVu = chucVu.getIdChucVu();
                    Log.d("l.e", "docIDChucVuBangTen: Tìm ID chức vụ có tên trùng khớp trong danh sách, ID = " + idChucVu + ", Tên = " + tenChucVu);
                    break;
                }
            }

            if (idChucVu != null) {
                nhanVien.setChucvu(idChucVu);
                Log.d("l.e", "docDuLieuChucVu: ID = " + idChucVu + ", Tên = " + tenChucVu + ", nhanVien.getChucvu() = " + nhanVien.getChucvu());
            } else {
                Log.d("l.e", "Không tìm thấy chức vụ với tên: " + tenChucVu);
            }
        } else
            Log.d("l.e", "docIDChucVuBangTen: listChucVuFireBase NULL, tên cv: " + tenChucVu);
    }

    // CUỐI: BẮT ĐIỀU KIỆN DỮ LIỆU ĐẦU VÀO
    private boolean batDieuKienDuLieuDauVao() {
        // kiểm tra có ký tự số trong tên nhân viên
        String tenNhanVien = addBinding.etTenNhanVien.getText().toString();
        for (int i = 0; i < tenNhanVien.length(); i++) {
            if (Character.isDigit(tenNhanVien.charAt(i))) {
                addBinding.etTenNhanVien.setError("Vui lòng không nhập số vào tên nhân viên");
                return false;
            }
        }

        if (addBinding.etTenNhanVien.getText().toString().isEmpty()) {
            addBinding.etTenNhanVien.setError("Vui lòng nhập đủ họ tên nhân viên");
            return false;
        }

        if (addBinding.etSDT.getText().toString().isEmpty() || addBinding.etSDT.getText().toString().length() != 10) {
            addBinding.etSDT.setError("Vui lòng nhập số điện thoại 10 số");
            return false;
        }

        if (addBinding.etEmail.getText().toString().isEmpty() || !addBinding.etEmail.getText().toString().contains("@") || !addBinding.etEmail.getText().toString().contains(".")) {
            addBinding.etEmail.setError("Vui lòng nhập đúng email");
            return false;
        }

        if (addBinding.etCCCD.getText().toString().isEmpty() || addBinding.etCCCD.getText().toString().length() != 10) {
            addBinding.etCCCD.setError("Vui lòng nhập CCCD (10 số)");
            return false;
        }

        if (addBinding.etPass.getText().toString().isEmpty() || addBinding.etPass.getText().toString().length() < 6) {
            addBinding.etPass.setError("Vui lòng nhập mật khẩu (từ 6 ký tự)");
            return false;
        }

//        if (addBinding.spinnerChucVu.getSelectedItemPosition() == 0) {
//            Toast.makeText(getContext(), "Vui lòng chọn chức vụ", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        return true;
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