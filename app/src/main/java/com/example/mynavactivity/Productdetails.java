package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Productdetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        Spinner spinnerMerchants=findViewById(R.id.spinner_merchants);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Productdetails.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.merchant));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMerchants.setAdapter(myAdapter);

        //=== Access Elements
        TextView prodName = findViewById(R.id.tv_productDetails_name);
        TextView prodPrice = findViewById(R.id.tv_productDetails_price);
        TextView prodDescription = findViewById(R.id.tv_productDetails_desc);
        ImageView prodImage = findViewById(R.id.iv_productDetails_image);

        Intent i = getIntent();
        String productName = i.getExtras().getString("prodName");
        double price = i.getDoubleExtra("prodPrice" , 0.0);
        System.out.println("Price " + price);

        String productDescription = i.getExtras().getString("prodDescription");
        String productImage = i.getExtras().getString("prodImage");

        prodName.setText(productName);
        prodPrice.setText("" + price);
        prodDescription.setText(productDescription);
        Glide.with(this)
                .load(productImage)
                .into(prodImage);

        ImageButton cartBtn = findViewById(R.id.btn_cart);
        cartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Productdetails.this, CartActivity.class);
            startActivity(intent);
        });

        Button addToCartBtn = findViewById(R.id.bt_addtocart);
        addToCartBtn.setOnClickListener(v -> {
            Toast.makeText(this,"Added To Cart" , Toast.LENGTH_SHORT).show();
        });
    }
}