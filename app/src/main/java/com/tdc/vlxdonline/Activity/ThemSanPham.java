package com.tdc.vlxdonline.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class ThemSanPham extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;


    EditText edtNhapten, edtNhapgiaban, edtNhapsoluong, edtDaban;
    Button btnThem, Xoa, Sua;
    ImageView ivImages;
    Uri uri;
    String imagesUrl;
    SanPham_Adapter adapter;
    SanPham_Model sanPhamModel = new SanPham_Model();
    List<SanPham_Model> list_SP = new ArrayList<>();
    List<String> list_id = new ArrayList<>();
    Integer id_ = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SanPham");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taosanpham_layout);
        setCtronl();
        key_auto();
        setEvent();
        //idAuto();
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
                            Toast.makeText(ThemSanPham.this, "Khong chon anh", Toast.LENGTH_SHORT).show();
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
        sanPhamModel.setImages(imagesUrl.toString());
        FirebaseDatabase.getInstance().getReference("SanPham").child("SP" + id_).setValue(sanPhamModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ThemSanPham.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
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
        ivImages = findViewById(R.id.ivImages);
        btnThem = findViewById(R.id.btnThem);
    }
}
