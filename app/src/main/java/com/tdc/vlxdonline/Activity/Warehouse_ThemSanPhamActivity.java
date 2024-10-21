package com.tdc.vlxdonline.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.tdc.vlxdonline.MainActivity;
import com.tdc.vlxdonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tdc.vlxdonline.Adapter.SanPham_Adapter;
import com.tdc.vlxdonline.Model.SanPham_Model;

import java.util.ArrayList;
import java.util.List;

/*import SanPham_Adapter.SanPham_Adapter;
import SanPham_Model.SanPham_Model;*/

public class Warehouse_ThemSanPhamActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;

    EditText edtNhapten, edtNhapgiaban, edtNhapsoluong, edtDaban,edtMoTa;
    Button btnThem, Xoa, Sua;
    ImageView ivImages;
    Uri uri;
    String imagesUrl;
    SanPham_Adapter adapter;
    SanPham_Model sanPhamModel = new SanPham_Model();
    List<SanPham_Model> list_SP = new ArrayList<>();
    List<String> list_id = new ArrayList<>();
    Integer id_ = 0;
    ValueEventListener listener;
    RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SanPham");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taosanpham_layout);
        setCtronl();
        key_auto();
        getDate();
        setEvent();

        //idAuto();
    }

    private void getDate() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SanPham_Adapter(Warehouse_ThemSanPhamActivity.this, list_SP);
        recyclerView.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference("SanPham");
        listener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list_SP.clear();
                for (DataSnapshot items : snapshot.getChildren()) {
                    SanPham_Model sanPhamModel = items.getValue(SanPham_Model.class);
                    list_SP.add(sanPhamModel);
                }
                // Notify adapter sau khi có dữ liệu
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
    }

    private void setEvent() {

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            ivImages.setImageURI(uri);
                        } else {
                            Toast.makeText(Warehouse_ThemSanPhamActivity.this, "Khong chon anh", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        ivImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                idAuto();
                uploadData();
//                Intent intent = new Intent(MainActivity.this, Warehouse_HomeActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

// Đảm bảo Adapter đã được khởi tạo trước khi thiết lập sự kiện click
        if (adapter != null) {
            adapter.setOnItemClickListener(new SanPham_Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Xử lý sự kiện click vào sản phẩm
                    if (position != RecyclerView.NO_POSITION) {
                        SanPham_Model sanPhamModel = list_SP.get(position);

                        // Hiển thị thông tin sản phẩm lên các EditText
                        edtNhapten.setText(sanPhamModel.getTenSP());
                        edtNhapgiaban.setText(sanPhamModel.getGiabanSP());
                        edtNhapsoluong.setText(sanPhamModel.getSoluong());
                        edtDaban.setText(sanPhamModel.getDaban());
edtMoTa.setText(sanPhamModel.getMoTa());
                        // Hiển thị hình ảnh sản phẩm
                        Glide.with(Warehouse_ThemSanPhamActivity.this)
                                .load(sanPhamModel.getImages())
                                .into(ivImages);
                    }
                }
            });
        } else {
            Toast.makeText(this, "Adapter chưa được khởi tạo", Toast.LENGTH_SHORT).show();
        }
    }

    public void key_auto() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SanPham");
        {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list_id.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String key = dataSnapshot.getKey();
                        if (key.isEmpty()) {
                            list_id.add("0");
                        } else {
                            list_id.add(key);
                        }
                    }
                    int size = list_id.size() + 1;
                    id_ = Integer.valueOf(size);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void saveDate() {
        sanPhamModel.setTenSP(edtNhapten.getText().toString());
        sanPhamModel.setGiabanSP(edtNhapgiaban.getText().toString());
        sanPhamModel.setSoluong(edtNhapsoluong.getText().toString());
        sanPhamModel.setDaban(edtDaban.getText().toString());
        sanPhamModel.setMoTa(edtMoTa.getText().toString());
        sanPhamModel.setImages(imagesUrl.toString());
        FirebaseDatabase.getInstance().getReference("SanPham").child("SP" + id_).setValue(sanPhamModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Warehouse_ThemSanPhamActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("SanPham Images")
                .child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imagesUrl = urlImage.toString();
                saveDate();
            }
        });
    }

    private void setCtronl() {
        edtNhapten = findViewById(R.id.edtNhapTen);
        edtNhapgiaban = findViewById(R.id.edtNhapgiaban);
        edtNhapsoluong = findViewById(R.id.edtNhapsoluong);
        edtDaban = findViewById(R.id.edtDaban);
        edtMoTa = findViewById(R.id.edtMoTa);
        ivImages = findViewById(R.id.ivImages);
        btnThem = findViewById(R.id.btnThem);
        recyclerView = findViewById(R.id.recycleview);
    }
}
