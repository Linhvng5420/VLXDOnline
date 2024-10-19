package com.tdc.vlxdonline.Activity;

import android.app.Activity;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

public class Owner_NhanVienDetailFragment extends Fragment {

    // Khai báo đối tượng NhanVien để lưu trữ thông tin nhân viên được chọn
    private NhanVien selectedNhanVien;
    // Khai báo đối tượng binding để tương tác với các thành phần trong giao diện (layout fragment_owner_nhanvien_detail.xml)
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    // Khai báo các giá trị mà bạn muốn hiển thị trong Spinner
    String[] chucVuArray = {"Kho", "Giao Hàng"};
    // Khai báo Spinner
    private Spinner spinnerChucVu;

    // Khai báo các hằng số để phân biệt các yêu cầu chọn ảnh
    private static final int PICK_IMAGE_AVATAR = 1;
    private static final int PICK_IMAGE_FRONT_ID = 2;
    private static final int PICK_IMAGE_BACK_ID = 3;

    // Khai báo Uri để lưu trữ đường dẫn của ảnh sau khi chọn
    private Uri avatarUri, frontIdUri, backIdUri;
    // Khai báo các ImageView cho avatar, CMND trước và CMND sau
    ImageView ivAvatar, ivFrontId, ivBackId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Gán binding cho layout fragment_owner_nhanvien_detail.xml bằng cách sử dụng phương thức inflate()
        nhanvienDetailBinding = FragmentOwnerNhanvienDetailBinding.inflate(inflater, container, false);
        // Trả về root của binding, tức là toàn bộ giao diện của fragment
        return nhanvienDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Phương thức này được gọi sau khi view đã được tạo
        super.onViewCreated(view, savedInstanceState);

        // Lấy Spinner từ View Binding sau khi binding đã được khởi tạo
        spinnerChucVu = nhanvienDetailBinding.spinnerChucVu;

        // Gán các ImageView từ View Binding
        ivAvatar = nhanvienDetailBinding.ivAvatar;
        ivFrontId = nhanvienDetailBinding.ivFrontId;
        ivBackId = nhanvienDetailBinding.ivBackId;

        // Thiết lập Toolbar cho Fragment
        EventToolbarBack(view);

        // Lấy thông tin từ đối tượng Bundle, kiểm tra nếu Bundle có dữ liệu được truyền vào
        EventNhanDuLieuBundle();

        // Thiết lập Spinner
        EventChonSpinner();

        // Thiết lập sự kiện cho nút Chỉnh Sửa
        EventBtnChinhSua();

        // Thiết lập sự kiện cho nút Lưu Lại
        EventBtnLuuLai();
    }

    // Thiết lập và xử lý sự kiện chọn chức vụ trong Spinner
    private void EventChonSpinner() {
        // Tạo ArrayAdapter sử dụng layout mặc định cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, chucVuArray);

        // Chỉ định layout cho từng item trong Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Gán adapter vào Spinner
        spinnerChucVu.setAdapter(adapter);
    }

    // Thiết lập sự kiện khi nhấn nút Lưu Lại
    private void EventBtnLuuLai() {
        nhanvienDetailBinding.btnLuuLai.setOnClickListener(v -> {
            // Vô hiệu hóa các trường EditText và hiển thị lại các phần đã ẩn
            nhanvienDetailBinding.etTenNhanVien.setEnabled(true);
            nhanvienDetailBinding.etSDT.setEnabled(true);
            nhanvienDetailBinding.etEmail.setEnabled(true);

            // Ẩn Spinner và hiển thị EditText
            nhanvienDetailBinding.tilChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.etChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.spinnerChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.tvChucVu.setVisibility(View.INVISIBLE);

            // Hiển thị nút Chỉnh Sửa
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.VISIBLE);

            // Ẩn nút Lưu Lại
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.GONE);
        });
    }

    // Thiết lập sự kiện khi nhấn nút Chỉnh Sửa, cho phép chọn ảnh
    private void EventBtnChinhSua() {
        nhanvienDetailBinding.btnChinhSua.setOnClickListener(v -> {
            // Cho phép chỉnh sửa các trường EditText
            nhanvienDetailBinding.etTenNhanVien.setEnabled(true);
            nhanvienDetailBinding.etSDT.setEnabled(true);
            nhanvienDetailBinding.etEmail.setEnabled(true);

            // Hiển thị Spinner và TextView Chức Vụ
            nhanvienDetailBinding.tilChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.etChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.spinnerChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.tvChucVu.setVisibility(View.VISIBLE);

            // Hiển thị nút Lưu Lại
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.GONE);

            // Thiết lập sự kiện nhấn vào các ảnh để chọn ảnh
            ivAvatar.setOnClickListener(v1 -> openImagePicker(PICK_IMAGE_AVATAR));
            ivFrontId.setOnClickListener(v1 -> openImagePicker(PICK_IMAGE_FRONT_ID));
            ivBackId.setOnClickListener(v1 -> openImagePicker(PICK_IMAGE_BACK_ID));
        });
    }

    // Phương thức mở trình chọn ảnh với loại ảnh được yêu cầu
    private void openImagePicker(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    // Xử lý kết quả khi người dùng chọn ảnh và hiển thị ảnh đã chọn lên ImageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (requestCode == PICK_IMAGE_AVATAR) {
                avatarUri = selectedImageUri;
                ivAvatar.setImageURI(avatarUri);
            } else if (requestCode == PICK_IMAGE_FRONT_ID) {
                frontIdUri = selectedImageUri;
                ivFrontId.setImageURI(frontIdUri);
            } else if (requestCode == PICK_IMAGE_BACK_ID) {
                backIdUri = selectedImageUri;
                ivBackId.setImageURI(backIdUri);
            }
        }
    }

    // Thiết lập Toolbar với nút quay lại và xử lý sự kiện quay lại
    private void EventToolbarBack(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Hiển thị nút quay về trên Toolbar
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý sự kiện nhấn nút quay về
        toolbar.setNavigationOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    // Nhận dữ liệu từ Bundle và hiển thị thông tin nhân viên
    private void EventNhanDuLieuBundle() {
        if (getArguments() != null) {
            selectedNhanVien = (NhanVien) getArguments().getSerializable("selectedNhanVien");

            // Hiển thị thông tin nhân viên lên giao diện
            nhanvienDetailBinding.etTenNhanVien.setText(selectedNhanVien.getTenNV());
            nhanvienDetailBinding.etChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");
            nhanvienDetailBinding.etSDT.setText(selectedNhanVien.getSdt());
            nhanvienDetailBinding.etEmail.setText(selectedNhanVien.getEmail());
        }
    }
}

