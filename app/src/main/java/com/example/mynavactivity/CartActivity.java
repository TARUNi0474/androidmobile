package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavactivity.homecategory.ApiCartAdapter;
import com.example.mynavactivity.retrofit.dto.CartItemDto;
import com.example.mynavactivity.retrofit.dto.CartItemsItem;
import com.example.mynavactivity.retrofit.model.ApiCart;
import com.example.mynavactivity.retrofit.network.iPostCartApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitCartBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements ApiCartAdapter.IApiCartResponseClick{
    String cEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ImageButton backBtn = findViewById(R.id.btn_goback);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomePageNavActivity.class);
            startActivity(intent);
        });
        //===Logged In User
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE);
        cEmail = sharedPreferences.getString("value","userName");

        //localRecyclerView();
        makeApi();

//        TextView removeBtn = findViewById(R.id.remove_item);
//        removeBtn.setOnClickListener(v -> {
//            makedeleteApi();
//        });
//        removeBtn.setOnClickListener(v -> {
//            makedeleteApi();
//        });
    }

//    public void makedeleteApi(){
//        Retrofit retrofit = RetrofitCartBuilder.getInstance();
//        Call<Void> responses = retrofit.create(iPostCartApi.class).delete(cEmail , 1L);
//        responses.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Toast.makeText(CartActivity.this,"Item Deleted" , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Toast.makeText(CartActivity.this,"Item Not Deleted" , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    public void onClick(ApiCart apiCart) {

        Toast.makeText(this,"You clicked Item" , Toast.LENGTH_SHORT).show();
    }

    public void makeApi(){
        Retrofit retrofit = RetrofitCartBuilder.getInstance();
        Call<CartItemDto> responses = retrofit.create(iPostCartApi.class).getAllItems(cEmail);
        responses.enqueue(new Callback<CartItemDto>() {
            @Override
            public void onResponse(Call<CartItemDto> call, Response<CartItemDto> response) {
                List<ApiCart> apiCartList = new ArrayList<>();
                System.out.println("response" + response.body().getCartItems());
                List<CartItemsItem> re = response.body().getCartItems();
                double totP = 0;
                for(int i = 0 ; i < re.size() ; i++){
                    Long ProductQuantity = re.get(i).getQuantity();
                    String ProductName = re.get(i).getProductName();
                    String ProductImage = re.get(i).getProductImage();
                    double price = re.get(i).getPrice();
                    Long pId = re.get(i).getProductId();
                    System.out.println("=====Pid===" + pId);
                    totP += price;
                    apiCartList.add(new ApiCart(pId,ProductName , ProductImage , price , ProductQuantity));
                }
                RecyclerView recyclerView = findViewById(R.id.cart_items_recyclerview);
                ApiCartAdapter apiCartAdapter = new ApiCartAdapter(apiCartList , CartActivity.this, getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                recyclerView.setAdapter(apiCartAdapter);
                TextView finalAmount = findViewById(R.id.total_cart_amount);
                finalAmount.setText("Rs. " + totP);
            }

            @Override
            public void onFailure(Call<CartItemDto> call, Throwable t) {
                Toast.makeText( CartActivity.this, "Something went wrong: from Cart Activity",Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    public void localRecyclerView(){
//        List<ApiCart> userDataList = new ArrayList<>();
//        generateUserData(userDataList);
//        RecyclerView recyclerView = findViewById(R.id.cart_items_recyclerview);
//        ApiCartAdapter apiCartAdapter = new ApiCartAdapter(userDataList , CartActivity.this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(apiCartAdapter);
//    }

//    private void generateUserData(List<ApiCart> userDataList) {
//        userDataList.add(new ApiCart("HP Pavlilion 1" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
//        userDataList.add(new ApiCart("HP Pavlilion 2" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
//        userDataList.add(new ApiCart("HP Pavlilion 3" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
//        userDataList.add(new ApiCart("HP Pavlilion 4" , "https://m.media-amazon.com/images/I/61vFO3R5UNL._SL1500_.jpg" , 194900 , 2 ));
//    }
}