package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mynavactivity.retrofit.dto.CartDto;
import com.example.mynavactivity.retrofit.dto.UserDto;
import com.example.mynavactivity.retrofit.network.iPostCartApi;
import com.example.mynavactivity.retrofit.network.iPostUserApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitCartBuilder;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitUserBuilder;
import com.google.gson.JsonElement;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Productdetails extends AppCompatActivity {
    TextView prodPrice;
    String uEmail;
    long prodId;
    double dPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        Spinner spinnerMerchants=findViewById(R.id.spinner_merchants);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Productdetails.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.merchant));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMerchants.setAdapter(myAdapter);

        //===Logged In User
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE);
        uEmail = sharedPreferences.getString("value","userName");

        //=== Access Elements
        TextView prodName = findViewById(R.id.tv_productDetails_name);
        prodPrice = findViewById(R.id.tv_productDetails_price);
        TextView prodDescription = findViewById(R.id.tv_productDetails_desc);
        ImageView prodImage = findViewById(R.id.iv_productDetails_image);

        Intent i = getIntent();
        String productName = i.getExtras().getString("prodName");
        double price = i.getDoubleExtra("prodPrice" , 0.0);
        System.out.println("Price " + price);

        String productDescription = i.getExtras().getString("prodDescription");
        String productImage = i.getExtras().getString("prodImage");
        prodId = i.getExtras().getLong("prodId");
        System.out.println("===========prodIdIN PD======" + prodId);

        prodName.setText(productName);
        prodPrice.setText("" + price);
        dPrice = Double.parseDouble(prodPrice.getText().toString());
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
            makeApi(createAddItemsRequest());
        });
    }

    public CartDto createAddItemsRequest(){
        CartDto cartDto = new CartDto();
        cartDto.setEmail(uEmail);
        cartDto.setProductId(prodId);
        cartDto.setMerchantId(1L);
        cartDto.setQuantity(1L);
        cartDto.setPrice(dPrice * cartDto.getQuantity());
        return cartDto;
    }

    public void makeApi(CartDto cartDto){
        Retrofit retrofit = RetrofitCartBuilder.getInstance();
        iPostCartApi iPostApi = retrofit.create(iPostCartApi.class);
        Call<Void> responses = iPostApi.addItem(cartDto);
        //System.out.println("cartId" + cartDto.getCartId() + "email" + cartDto.getEmail() + "prodId" + cartDto.getProductId() + "mecrhcId" + cartDto.getMerchantId() + "quant" + cartDto.getQuantity());
        responses.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //System.out.println("response : " + response.body().toString());
                Toast.makeText(Productdetails.this,"Added To Cart" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Productdetails.this, CartActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Productdetails.this,"Problem in Cart" , Toast.LENGTH_SHORT).show();

            }
        });
    }
}