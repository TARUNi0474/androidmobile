package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavactivity.adapter.ApiProductAdapter;
import com.example.mynavactivity.retrofit.dto.ProductIDNameDto;
import com.example.mynavactivity.retrofit.model.ApiProduct;
import com.example.mynavactivity.retrofit.network.iPostProductApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitProductBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity implements ApiProductAdapter.IApiProductResponseClick{
    private RecyclerView categoryRecyclerView;
    List<ProductIDNameDto> productIDNameDtoList;
    @Override
    public void onClick(ApiProduct apiProduct) {
        Intent intent = new Intent(ProductsActivity.this, Productdetails.class);
        intent.putExtra("prodName" , apiProduct.getProductName());
        intent.putExtra("prodPrice" , ((Double) apiProduct.getPrice()));
        intent.putExtra("prodDescription" , apiProduct.getProductDescription());
        intent.putExtra("prodImage" , apiProduct.getProductImage());
        System.out.println("====ProductName=== " + apiProduct.getProductName());
        for(int i = 0 ; i < productIDNameDtoList.size() ; i++){
            if(productIDNameDtoList.get(i).getProductName().equals(apiProduct.getProductName())){
                intent.putExtra("prodId" , productIDNameDtoList.get(i).getProductId());
                System.out.println("Name : " + productIDNameDtoList.get(i).getProductName() + " ProductId : " + productIDNameDtoList.get(i).getProductId());
            }
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ImageButton cartBtn = findViewById(R.id.btn_cart);
        cartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProductsActivity.this, CartActivity.class);
            startActivity(intent);
        });

        ImageButton bckBtn = findViewById(R.id.btn_gbck);
        bckBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProductsActivity.this, HomePageNavActivity.class);
            startActivity(intent);
        });

        makeApi();
    }

    public void makeApi(){
        Retrofit retrofit = RetrofitProductBuilder.getInstance();
        iPostProductApi iPostApi = retrofit.create(iPostProductApi.class);
        Intent i = getIntent();
        long catId = i.getExtras().getLong("category");
        String catName = i.getExtras().getString("categoryName");
        TextView tvCatName = findViewById(R.id.tv_placeholder_product);
        tvCatName.setText(catName);
        Call<List<ApiProduct>> responses = iPostApi.getByCategoryId(catId);
        responses.enqueue(new Callback<List<ApiProduct>>() {
            @Override
            public void onResponse(Call<List<ApiProduct>> call, Response<List<ApiProduct>> response) {
                List<ApiProduct> apiProductArrayList = new ArrayList<>();
                productIDNameDtoList = new ArrayList<>();
                for(int i = 0 ; i<response.body().size();i++){
                    String productImage = response.body().get(i).getProductImage();
                    String productName = response.body().get(i).getProductName();
                    Double price = response.body().get(i).getPrice();
                    Long proId = response.body().get(i).getProductId();
                    String productDescription = response.body().get(i).getProductDescription();
//                    System.out.println("===========prodIdPAA======" + response.body().get(i).getProductId());
                    System.out.println("Price PA "+response.body().get(i).getPrice() + " Name PA " + response.body().get(i).getProductName());
                    apiProductArrayList.add(new ApiProduct(productImage,productName,price,productDescription));
                    productIDNameDtoList.add(new ProductIDNameDto(proId , productName ));
                }
                RecyclerView recyclerView = findViewById(R.id.recyclerCatView);
                ApiProductAdapter apiProductAdapter = new ApiProductAdapter(apiProductArrayList , ProductsActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductsActivity.this));
                recyclerView.setAdapter(apiProductAdapter);
            }

            @Override
            public void onFailure(Call<List<ApiProduct>> call, Throwable t) {
                Toast.makeText( ProductsActivity.this, "Something went wrong: from Products Activity",Toast.LENGTH_SHORT).show();
            }
        });
    }
}