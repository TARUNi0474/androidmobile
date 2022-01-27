package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavactivity.homecategory.ApiCartAdapter;
import com.example.mynavactivity.retrofit.model.ApiCart;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements ApiCartAdapter.IApiCartResponseClick{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ImageButton backBtn = findViewById(R.id.btn_goback);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomePageNavActivity.class);
            startActivity(intent);
        });
        localRecyclerView();
    }

    @Override
    public void onClick(ApiCart apiCart) {
        Toast.makeText(this,"You clicked Item" , Toast.LENGTH_SHORT).show();
    }

    public void localRecyclerView(){
        List<ApiCart> userDataList = new ArrayList<>();
        generateUserData(userDataList);
        RecyclerView recyclerView = findViewById(R.id.cart_items_recyclerview);
        ApiCartAdapter apiCartAdapter = new ApiCartAdapter(userDataList , CartActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(apiCartAdapter);
    }

    private void generateUserData(List<ApiCart> userDataList) {
        userDataList.add(new ApiCart("HP Pavlilion 1" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
        userDataList.add(new ApiCart("HP Pavlilion 2" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
        userDataList.add(new ApiCart("HP Pavlilion 3" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
        userDataList.add(new ApiCart("HP Pavlilion 4" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
    }
}