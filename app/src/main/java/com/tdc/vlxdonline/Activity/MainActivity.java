package com.tdc.vlxdonline.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tdc.vlxdonline.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc;
//    static ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setControl();
        setEvents();
    }

    private void setEvents() {
        KhoiTao();
        HomeCustomerActivity homeCustomerActivity = new HomeCustomerActivity();
        homeCustomerActivity.loai = 1;
    }

    private void KhoiTao() {
//        ArrayList<Categorys> arr = new ArrayList<>();
//
//        arr.add(new Categorys("Thep", 1, R.drawable.tc));
//        arr.add(new Categorys("Cate 2", 2, R.drawable.star));
//        arr.add(new Categorys("Cate 3", 3, R.drawable.ic_launcher_foreground));
//        arr.add(new Categorys("Cate 4", 4, R.drawable.star_disable));
//        arr.add(new Categorys("Cate 5", 5, R.drawable.bg_detail));
//
//        LinearLayoutManager layout = new LinearLayoutManager(this);
//        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rc.setLayoutManager(layout);
//        CategoryAdapter adap = new CategoryAdapter(this, arr);
//        rc.setAdapter(adap);
    }

    private void setControl() {
//        rc = findViewById(R.id.rcView);
//        image = findViewById(R.id.imgDetail);
    }
}