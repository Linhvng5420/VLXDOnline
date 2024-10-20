package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

public class Owner_NhanVienDetailFragment extends Fragment {

    // Khai báo đối tượng NhanVien để lưu trữ thông tin nhân viên được chọn
    private NhanVien selectedNhanVien;
    // Khai báo đối tượng binding để tương tác với các thành phần trong giao diện (layout fragment_owner_nhanvien_detail.xml)
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    // Khai báo DatabaseReference để kết nối với Firebase
    private DatabaseReference databaseReference;

    // Khai báo Spinner và danh sách chức vụ cho nhân viên
    private Spinner spinnerChucVu;
    private String[] chucVuArray = {"Kho", "Giao Hàng"};

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

        // Lấy Spinner từ View Binding
        spinnerChucVu = nhanvienDetailBinding.spinnerChucVu;

        // Thiết lập Toolbar cho Fragment
        setupToolbar(view);

        // Lấy thông tin từ đối tượng Bundle nếu có
        retrieveDataFromBundle();

        // Xử lý Spinner chọn chức vụ
        setupSpinner();

        // Thiết lập sự kiện cho nút Chỉnh Sửa
        setupEditButton();

        // Thiết lập sự kiện cho nút Lưu Lại
        setupSaveButton();

        // Thiết lập sự kiện cho nút Xóa
        setupDeleteButton();

        // Thiết lập sự kiện cho nút Hủy
        setupCancelButton();
    }

    // Thiết lập Toolbar và điều hướng
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

    // Nhận dữ liệu từ Bundle và hiển thị thông tin
    private void retrieveDataFromBundle() {
        if (getArguments() != null) {
            // Lấy thông tin nhân viên từ Bundle
            selectedNhanVien = (NhanVien) getArguments().getSerializable("selectedNhanVien");

            // Hiển thị thông tin nhân viên lên giao diện
            nhanvienDetailBinding.tvIDNhanVien.setText("ID: " + selectedNhanVien.getID());
            nhanvienDetailBinding.etTenNhanVien.setText(selectedNhanVien.getTenNV());
            nhanvienDetailBinding.etChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");
            nhanvienDetailBinding.etSDT.setText(selectedNhanVien.getSDT());
            nhanvienDetailBinding.etEmail.setText(selectedNhanVien.getEmail());
        }
    }

    // Xử lý Spinner chọn chức vụ
    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, chucVuArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChucVu.setAdapter(adapter);

        // Thiết lập lựa chọn mặc định cho Spinner dựa trên chức vụ của nhân viên
        spinnerChucVu.setSelection(selectedNhanVien.getChucVu()); // 0 cho "Kho" và 1 cho "Giao Hàng"
    }

    // Thiết lập sự kiện cho nút Chỉnh Sửa
    private void setupEditButton() {
        nhanvienDetailBinding.btnChinhSua.setOnClickListener(v -> {
            // Kích hoạt chỉnh sửa các trường thông tin
            nhanvienDetailBinding.etTenNhanVien.setEnabled(true);
            nhanvienDetailBinding.etSDT.setEnabled(true);
            nhanvienDetailBinding.etEmail.setEnabled(true);

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

    // Thiết lập sự kiện cho nút Xóa
    private void setupDeleteButton() {
        nhanvienDetailBinding.btnXoa.setOnClickListener(view -> {
            // Tạo hộp thoại xác nhận
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác Nhận")
                    .setMessage("Bạn có chắc chắn muốn xóa nhân viên không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        // Xóa nhân viên khỏi Firebase Database
                        databaseReference = FirebaseDatabase.getInstance().getReference("nhanVien").child(selectedNhanVien.getID()+"");
                        
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

    // Thiết lập sự kiện cho nút Lưu Lại
    private void setupSaveButton() {
        nhanvienDetailBinding.btnLuuLai.setOnClickListener(v -> {
            // Tạo hộp thoại xác nhận
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác Nhận")
                    .setMessage("Bạn có chắc chắn muốn lưu thay đổi không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        // Lưu giá trị Chức vụ từ Spinner
                        selectedNhanVien.setChucVu(spinnerChucVu.getSelectedItemPosition()); // Lưu 0 cho "Kho" và 1 cho "Giao Hàng"

                        // Cập nhật thông tin nhân viên trong Firebase
                        databaseReference.child(String.valueOf(selectedNhanVien.getID())).setValue(selectedNhanVien)
                                .addOnSuccessListener(aVoid -> {
                                    // Vô hiệu hóa các trường chỉnh sửa sau khi lưu
                                    nhanvienDetailBinding.etTenNhanVien.setEnabled(false);
                                    nhanvienDetailBinding.etSDT.setEnabled(false);
                                    nhanvienDetailBinding.etEmail.setEnabled(false);

                                    // Ẩn Spinner và hiển thị TextView cho chức vụ
                                    nhanvienDetailBinding.tilChucVu.setVisibility(View.VISIBLE);
                                    nhanvienDetailBinding.etChucVu.setVisibility(View.VISIBLE);
                                    nhanvienDetailBinding.spinnerChucVu.setVisibility(View.INVISIBLE);
                                    nhanvienDetailBinding.tvChucVu.setVisibility(View.INVISIBLE);

                                    // Ẩn nút Lưu Lại, Xóa, Hủy và Hiển thị nút Sửa sau khi lưu
                                    nhanvienDetailBinding.btnLuuLai.setVisibility(View.INVISIBLE);
                                    nhanvienDetailBinding.btnHuy.setVisibility(View.VISIBLE);
                                    nhanvienDetailBinding.btnXoa.setVisibility(View.INVISIBLE);
                                    nhanvienDetailBinding.btnHuy.setVisibility(View.INVISIBLE);
                                    nhanvienDetailBinding.btnChinhSua.setVisibility(View.VISIBLE);

                                    // Cập nhật giao diện với thông tin mới
                                    nhanvienDetailBinding.etChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");

                                    // Hiển thị thông báo lưu thành công
                                    Toast.makeText(getContext(), "Đã lưu thành công!", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> {
                                    // Hiển thị thông báo lỗi nếu không lưu được
                                    Toast.makeText(getContext(), "Lỗi khi lưu thông tin: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    })
                    .setNegativeButton("Không", null) // Hiển thị hộp thoại
                    .show();
        });
    }

    // Thiết lập sự kiện cho nút Hủy
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
}

