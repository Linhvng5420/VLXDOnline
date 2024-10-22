package com.tdc.vlxdonline.Activity;

import android.os.Bundle;
import android.util.Log;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdc.vlxdonline.Model.NhanVien;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.FragmentOwnerNhanvienDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class Owner_NhanVienDetailFragment extends Fragment {
    private FragmentOwnerNhanvienDetailBinding nhanvienDetailBinding;

    // Khai báo DatabaseReference để kết nối với Firebase
    private DatabaseReference databaseReference;
    private NhanVien nhanVien;

    // Lưu ID nhân viên đc truyền từ Fragment trước qua
    private String selectedIDNhanVien;

    // Khai báo Spinner và danh sách chức vụ cho nhân viên
    private Spinner spinnerChucVu = nhanvienDetailBinding.spinnerChucVu;
    private String[] chucVuArray;

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
        // Thiết lập Toolbar cho Fragment
        setupToolbar(view);

        // Lấy thông tin IDNV từ Bundle và lấy nhân viên từ firebase
        nhanIDNhanVienTuBundle();
//        layNhanVienTuFirebase();

        /*
        // Xử lý Spinner chọn chức vụ
        setupSpinner();

        // Thiết lập sự kiện cho nút Chỉnh Sửa
        setupEditButton();

        // Thiết lập sự kiện cho nút Lưu Lại
        setupSaveButton();

        // Thiết lập sự kiện cho nút Xóa
        setupDeleteButton();

        // Thiết lập sự kiện cho nút Hủy
        setupCancelButton();*/
    }

    private void layNhanVienTuFirebase() {
        // Khởi tạo databaseReference và lấy dữ liệu từ Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("nhanvien");

        // Lấy dữ liệu của nhân viên theo ID
        databaseReference.child(selectedIDNhanVien).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Kiểm tra xem có dữ liệu không
                if (dataSnapshot.exists()) {
                    // Chuyển đổi dữ liệu từ DataSnapshot thành đối tượng NhanVien
                    nhanVien = dataSnapshot.getValue(NhanVien.class);
                    Log.d("l.e", "Nhân viên: " + nhanVien);
                } else {
                    // Xử lý trường hợp không tìm thấy nhân viên
                    Log.e("l.e", "Nhân viên không tồn tại");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
                Log.e("l.e", "Lỗi: " + databaseError.getMessage());
            }
        });
    }


    // Nhận dữ liệu từ Bundle và hiển thị thông tin
    private void nhanIDNhanVienTuBundle() {
        // getArguments là một phương thức của Fragment để truy cập Bundle
        if (getArguments() != null) {
            // Lấy thông tin nhân viên từ Bundle
            selectedIDNhanVien = getArguments().getSerializable("selectedIDNhanVien").toString();

            // Hiển thị thông tin ID nhân viên lên giao diện
            nhanvienDetailBinding.tvIDNhanVien.setText("ID: " + selectedIDNhanVien);

            //lấy thông tin nhân viên từ firebase thông qua ID

        } else
            Log.d("l.e", "nhanIDNhanVienTuBundle: Lỗi truyền bundle từ fragment Nhan viên qua Detail");
    }
    /*

    private void setupSpinner() {
        // Khởi tạo Firebase DatabaseReference cho nhánh "chucvu"
        DatabaseReference chucVuReference = FirebaseDatabase.getInstance().getReference("chucvu");

        // Lắng nghe sự thay đổi của dữ liệu từ Firebase
        chucVuReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Tạo danh sách chứa tên các chức vụ
                List<String> chucVuList = new ArrayList<>();

                // Duyệt qua các chức vụ trong Firebase và thêm vào danh sách
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String chucVu = snapshot.child("ten").getValue(String.class);
                    chucVuList.add(chucVu);
                }

                // Chuyển danh sách thành mảng để sử dụng cho Spinner
                chucVuArray = chucVuList.toArray(new String[0]);

                // Tạo ArrayAdapter cho Spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, chucVuArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerChucVu.setAdapter(adapter);

                // Thiết lập lựa chọn mặc định cho Spinner dựa trên chức vụ của nhân viên
                spinnerChucVu.setSelection(selectedIDNhanVien.getChucVu());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu không thể đọc dữ liệu
                Toast.makeText(getContext(), "Lỗi khi tải dữ liệu chức vụ: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

*/

    /*// Thiết lập sự kiện cho nút Chỉnh Sửa
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
                        databaseReference = FirebaseDatabase.getInstance().getReference("nhanVien").child(selectedIDNhanVien.getID() + "");

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
                        selectedIDNhanVien.setChucVu(spinnerChucVu.getSelectedItemPosition()); // Lưu 0 cho "Kho" và 1 cho "Giao Hàng"

                        // Cập nhật thông tin nhân viên trong Firebase
                        databaseReference.child(String.valueOf(selectedIDNhanVien.getID())).setValue(selectedIDNhanVien)
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
                                    nhanvienDetailBinding.etChucVu.setText(selectedIDNhanVien.getChucVu() == 0 ? "Kho" : "Giao Hàng");

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
    }*/

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
}

