package com.tdc.vlxdonline.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

public class Owner_NhanVienDetailFragment extends Fragment {

    // Khai báo đối tượng NhanVien để lưu trữ thông tin nhân viên được chọn
    private NhanVien selectedNhanVien;
    // Khai báo đối tượng binding để tương tác với các thành phần trong giao diện (layout fragment_owner_nhanvien_detail.xml)
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    // Khai báo Spinner và danh sách chức vụ cho nhân viên
    private Spinner spinnerChucVu;
    String[] chucVuArray = {"Kho", "Giao Hàng"};

    // Mã yêu cầu cho việc chọn ảnh
    private static final int PICK_IMAGE_AVATAR = 1;
    private static final int PICK_IMAGE_FRONT_ID = 2;
    private static final int PICK_IMAGE_BACK_ID = 3;

    // Uri để lưu trữ đường dẫn đến ảnh được chọn
    private Uri avatarUri, frontIdUri, backIdUri;

    // Các ImageView đại diện cho ảnh avatar, ảnh CMND trước và sau
    private ImageView ivAvatar, ivFrontId, ivBackId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // Gán các ImageView từ View Binding
        ivAvatar = nhanvienDetailBinding.ivAvatar;
        ivFrontId = nhanvienDetailBinding.ivFrontId;
        ivBackId = nhanvienDetailBinding.ivBackId;

        // Lấy Spinner từ View Binding
        spinnerChucVu = nhanvienDetailBinding.spinnerChucVu;

        // Thiết lập Toolbar cho Fragment
        EventToolbarBack(view);

        // Lấy thông tin từ đối tượng Bundle nếu có
        EventNhanDuLieuBundle();

        // Xử lý Spinner chọn chức vụ
        EventChonSpinner();

        // Thiết lập sự kiện cho nút Chỉnh Sửa
        EventBtnChinhSua();

        // Thiết lập sự kiện cho nút Lưu Lại
        EventBtnLuuLai();
    }

    // =======================================
    // Phần chức năng: Thiết lập Toolbar và điều hướng
    // =======================================

    private void EventToolbarBack(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý khi nhấn nút quay về trên Toolbar
        toolbar.setNavigationOnClickListener(v -> getParentFragmentManager().popBackStack());
    }

    // =======================================
    // Phần chức năng: Nhận dữ liệu từ Bundle và hiển thị thông tin
    // =======================================

    private void EventNhanDuLieuBundle() {
        if (getArguments() != null) {
            // Lấy thông tin nhân viên từ Bundle
            selectedNhanVien = (NhanVien) getArguments().getSerializable("selectedNhanVien");

            // Hiển thị thông tin nhân viên lên giao diện
            nhanvienDetailBinding.etTenNhanVien.setText(selectedNhanVien.getTenNV());
            nhanvienDetailBinding.etChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");
            nhanvienDetailBinding.etSDT.setText(selectedNhanVien.getSdt());
            nhanvienDetailBinding.etEmail.setText(selectedNhanVien.getEmail());
        }
    }

    // =======================================
    // Phần chức năng: Xử lý Spinner chọn chức vụ
    // =======================================

    private void EventChonSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, chucVuArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChucVu.setAdapter(adapter);
    }

    // =======================================
    // Phần chức năng: Thiết lập sự kiện cho nút Chỉnh Sửa
    // =======================================

    private void EventBtnChinhSua() {
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

            // Hiển thị nút Lưu Lại
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.GONE);

            // Cho phép người dùng chọn ảnh khi nhấn vào các ImageView
            ivAvatar.setOnClickListener(v1 -> openImagePicker(PICK_IMAGE_AVATAR));
            ivFrontId.setOnClickListener(v1 -> openImagePicker(PICK_IMAGE_FRONT_ID));
            ivBackId.setOnClickListener(v1 -> openImagePicker(PICK_IMAGE_BACK_ID));
        });
    }

    // =======================================
    // Phần chức năng: Thiết lập sự kiện cho nút Lưu Lại
    // =======================================

    private void EventBtnLuuLai() {
        nhanvienDetailBinding.btnLuuLai.setOnClickListener(v -> {
            // Vô hiệu hóa các trường chỉnh sửa sau khi lưu
            nhanvienDetailBinding.etTenNhanVien.setEnabled(false);
            nhanvienDetailBinding.etSDT.setEnabled(false);
            nhanvienDetailBinding.etEmail.setEnabled(false);

            // Ẩn Spinner và hiển thị TextView cho chức vụ
            nhanvienDetailBinding.tilChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.etChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.spinnerChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.tvChucVu.setVisibility(View.INVISIBLE);

            // Ẩn nút Lưu Lại sau khi lưu
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.GONE);
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.VISIBLE);

            // Vô hiệu hóa sự kiện onClick của các ImageView (ngăn người dùng chọn ảnh sau khi lưu)
            ivAvatar.setOnClickListener(null);
            ivFrontId.setOnClickListener(null);
            ivBackId.setOnClickListener(null);
        });
    }

    // =======================================
    // Phần chức năng: Mở trình chọn ảnh
    // =======================================

    private void openImagePicker(int requestCode) {
        //ACTION_GET_CONTENT: cho phép chọn một tệp từ bất kỳ nguồn nào, bao gồm cả trình quản lý tệp và các ứng dụng khác.
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); // Chỉ định loại tệp là hình ảnh
        intent.addCategory(Intent.CATEGORY_OPENABLE); // Thêm thể loại này để đảm bảo rằng trình quản lý tệp hiển thị các tệp có thể mở được.
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), requestCode); // Mở trình hộp thoại chọn ảnh
    }

    // =======================================
    // Phần chức năng: Xử lý ảnh sau khi chọn
    // =======================================

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Kiểm tra kết quả trả về từ hoạt động chọn ảnh
        if (resultCode == Activity.RESULT_OK && data != null) {
            // Lấy Uri của ảnh đã chọn từ Intent data
            Uri selectedImageUri = data.getData();

            // Dựa vào mã yêu cầu để xác định ảnh nào đã được chọn
            if (requestCode == PICK_IMAGE_AVATAR) {
                // Lưu Uri ảnh đại diện và hiển thị ảnh trong ImageView
                avatarUri = selectedImageUri;
                ivAvatar.setImageURI(avatarUri); // Hiển thị ảnh đại diện
            } else if (requestCode == PICK_IMAGE_FRONT_ID) {
                // Lưu Uri ảnh CMND trước và hiển thị ảnh trong ImageView
                frontIdUri = selectedImageUri;
                ivFrontId.setImageURI(frontIdUri); // Hiển thị ảnh CMND trước
            } else if (requestCode == PICK_IMAGE_BACK_ID) {
                // Lưu Uri ảnh CMND sau và hiển thị ảnh trong ImageView
                backIdUri = selectedImageUri;
                ivBackId.setImageURI(backIdUri); // Hiển thị ảnh CMND sau
            }
        }
    }

    // =======================================
    // Phần chức năng: Kiểm tra và yêu cầu quyền truy cập
    // =======================================
    private void checkPermissions() {
        // Kiểm tra xem ứng dụng có quyền truy cập vào bộ nhớ ngoài hay không
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Nếu chưa có quyền, yêu cầu quyền truy cập từ người dùng
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_AVATAR);
        }
    }

}
