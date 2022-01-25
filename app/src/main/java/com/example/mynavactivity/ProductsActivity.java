package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mynavactivity.homecategory.ApiProductAdapter;
import com.example.mynavactivity.homecategory.model.ApiProduct;
import com.example.mynavactivity.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements ApiProductAdapter.IApiProductResponseClick{

    @Override
    public void onClick(ApiProduct apiProduct) {
        Intent intent = new Intent(ProductsActivity.this, Productdetails.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ImageButton backBtn = findViewById(R.id.pd_bt_back);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProductsActivity.this, HomePageNavActivity.class);
            startActivity(intent);
        });
//        ImageButton backBtn = findViewById(R.id.bt_back);
//        backBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(ProductsActivity.this, HomePageNavActivity.class);
//            startActivity(intent);
//        });
        localRecyclerView();
    }

    public void localRecyclerView(){
        List<ApiProduct> userDataList = new ArrayList<>();
        generateUserData(userDataList);
        RecyclerView recyclerView = findViewById(R.id.recyclerCatView);
        ApiProductAdapter apiProductAdapter = new ApiProductAdapter(userDataList , ProductsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(apiProductAdapter);
    }

    private void generateUserData(List<ApiProduct> userDataList) {
        userDataList.add(new ApiProduct(R.drawable.ic_logout , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_menu_camera , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_order , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_cart , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_menu_gallery , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_logout , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_menu_camera , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_order , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_cart , "Lenovo Chromebook" , 20990 ));
        userDataList.add(new ApiProduct(R.drawable.ic_menu_gallery , "Lenovo Chromebook" , 20990 ));
    }
}