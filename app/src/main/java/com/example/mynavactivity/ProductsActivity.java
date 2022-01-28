package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavactivity.homecategory.ApiProductAdapter;
import com.example.mynavactivity.retrofit.model.ApiProduct;
import com.example.mynavactivity.retrofit.network.iPostProductApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitProductBuilder;
import com.example.mynavactivity.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductsActivity extends AppCompatActivity implements ApiProductAdapter.IApiProductResponseClick{
    private RecyclerView categoryRecyclerView;
    @Override
    public void onClick(ApiProduct apiProduct) {
        Intent intent = new Intent(ProductsActivity.this, Productdetails.class);
        intent.putExtra("prodName" , apiProduct.getProductName());
        intent.putExtra("prodPrice" , ((Double) apiProduct.getPrice()));
        intent.putExtra("prodDescription" , apiProduct.getProductDescription());
        intent.putExtra("prodImage" , apiProduct.getProductImage());
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

        //localRecyclerView();
        makeApi();
    }

    public void localRecyclerView(){
        List<ApiProduct> userDataList = new ArrayList<>();
        generateUserData(userDataList);
        RecyclerView recyclerView = findViewById(R.id.recyclerCatView);
        ApiProductAdapter apiProductAdapter = new ApiProductAdapter(userDataList , ProductsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(apiProductAdapter);
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
                for(int i = 0 ; i<response.body().size();i++){
                    String productImage = response.body().get(i).getProductImage();
                    String productName = response.body().get(i).getProductName();
                    Double price = response.body().get(i).getPrice();
                    String productDescription = response.body().get(i).getProductDescription();
                    System.out.println("Price PA "+response.body().get(i).getPrice() + " Name PA " + response.body().get(i).getProductName());
                    apiProductArrayList.add(new ApiProduct(productImage,productName,price,productDescription));
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


    private void generateUserData(List<ApiProduct> userDataList) {
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));

    }
}