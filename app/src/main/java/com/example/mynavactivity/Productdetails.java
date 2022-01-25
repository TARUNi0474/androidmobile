package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Productdetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        Spinner spinnerMerchants=findViewById(R.id.spinner_merchants);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Productdetails.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.merchant));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMerchants.setAdapter(myAdapter);
        ImageButton backBtn = findViewById(R.id.pd_bt_back);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Productdetails.this, HomePageNavActivity.class);
            startActivity(intent);
        });
        Button addToCartBtn = findViewById(R.id.bt_addtocart);
        addToCartBtn.setOnClickListener(v -> {
            Toast.makeText(this,"Added To Cart" , Toast.LENGTH_SHORT).show();
        });
    }
}