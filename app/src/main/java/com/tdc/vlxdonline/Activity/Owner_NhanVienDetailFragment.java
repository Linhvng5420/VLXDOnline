package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Phương thức onCreate để khởi tạo fragment, gọi super để sử dụng các phương thức của Fragment
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

        // TODO: Thiết lập Toolbar cho Fragment (sử dụng Toolbar trong giao diện để thay thế ActionBar)
        EventToolbarBack(view);

        // TODO: Lấy thông tin từ đối tượng Bundle, kiểm tra nếu Bundle có dữ liệu được truyền vào
        EventNhanDuLieuBundle();

        // TODO: Xử Lý Spinner
        EventChonSpinner();

        // TODO: Thiết lập sự kiện cho nút Chỉnh Sửa
        EventBtnChinhSua();

        // TODO: Thiết lập sự kiện cho nút Lưu Lại
        EventBtnLuuLai();
    }

    private void EventChonSpinner() {
        // Tạo ArrayAdapter sử dụng layout mặc định cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, chucVuArray);

        // Chỉ định layout cho từng item trong Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Gán adapter vào Spinner
        spinnerChucVu.setAdapter(adapter);
    }

    private void EventBtnLuuLai() {
        // Thiết lập sự kiện khi nhấn nút btnLuuLai
        nhanvienDetailBinding.btnLuuLai.setOnClickListener(v -> {
            // Vô hiệu hóa các trường EditText
            nhanvienDetailBinding.etTenNhanVien.setEnabled(true);
            nhanvienDetailBinding.etSDT.setEnabled(true);
            nhanvienDetailBinding.etEmail.setEnabled(true);

            // Ẩn Spinner và TextView Chức Vụ, hiển thị EditText
            nhanvienDetailBinding.tilChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.etChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.spinnerChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.tvChucVu.setVisibility(View.INVISIBLE);

            // Hiển thị nút Edit (nút này đã bị ẩn khi bắt sự kiện nhấn nút Chỉnh Sửa)
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.VISIBLE);

            // Ẩn nút Lưu Lại sau khi nhấn
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.GONE);
        });
    }

    private void EventBtnChinhSua() {
        // Thiết lập sự kiện khi nhấn nút btnChinhSua
        nhanvienDetailBinding.btnChinhSua.setOnClickListener(v -> {
            // Cho phép chỉnh sửa các trường EditText bằng cách kích hoạt chúng
            nhanvienDetailBinding.etTenNhanVien.setEnabled(true);
            nhanvienDetailBinding.etSDT.setEnabled(true);
            nhanvienDetailBinding.etEmail.setEnabled(true);

            // Hiển thị Spinner và TextView Chức Vụ, ẩn Text
            nhanvienDetailBinding.tilChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.etChucVu.setVisibility(View.INVISIBLE);
            nhanvienDetailBinding.spinnerChucVu.setVisibility(View.VISIBLE);
            nhanvienDetailBinding.tvChucVu.setVisibility(View.VISIBLE);

            // Hiển thị nút Lưu Lại (nút này ban đầu ẩn)
            nhanvienDetailBinding.btnLuuLai.setVisibility(View.VISIBLE);

            // Ẩn nút Chỉnh Sửa sau khi nhấn
            nhanvienDetailBinding.btnChinhSua.setVisibility(View.GONE);
        });
    }

    private void EventToolbarBack(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // Kiểm tra và hiển thị nút "quay về" trên Toolbar nếu ActionBar tồn tại
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            // Hiển thị nút quay về trên ActionBar
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Hiển thị nút quay về trên Toolbar
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý sự kiện khi nhấn nút quay về trên Toolbar
        toolbar.setNavigationOnClickListener(v -> {
            // Quay về Fragment trước đó trong ngăn xếp (back stack)
            getParentFragmentManager().popBackStack();
        });
    }

    private void EventNhanDuLieuBundle() {
        if (getArguments() != null) {
            // Lấy thông tin nhân viên từ Bundle bằng cách chuyển đổi đối tượng Serializable
            selectedNhanVien = (NhanVien) getArguments().getSerializable("selectedNhanVien");

            // Ghi lại thông tin nhân viên trong log để kiểm tra
            Log.d("selectedNhanVien", selectedNhanVien.toString());

            // Hiển thị thông tin nhân viên lên các thành phần giao diện (TextView) thông qua binding
            nhanvienDetailBinding.etTenNhanVien.setText(selectedNhanVien.getTenNV());
            // Hiển thị chức vụ nhân viên, nếu giá trị ChucVu là 0 thì hiển thị "Kho", nếu là 1 thì hiển thị "Giao Hàng"
            nhanvienDetailBinding.etChucVu.setText(selectedNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");
            // Hiển thị số điện thoại của nhân viên
            nhanvienDetailBinding.etSDT.setText(selectedNhanVien.getSdt());
            // Hiển thị email của nhân viên
            nhanvienDetailBinding.etEmail.setText(selectedNhanVien.getEmail());
        }
    }

}
