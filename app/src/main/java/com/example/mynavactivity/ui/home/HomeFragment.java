package com.example.mynavactivity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynavactivity.Productdetails;
import com.example.mynavactivity.ProductsActivity;
import com.example.mynavactivity.R;
import com.example.mynavactivity.homecategory.ApiOrderAdapter;
import com.example.mynavactivity.homecategory.ApiProductAdapter;
import com.example.mynavactivity.retrofit.model.ApiProduct;
import com.example.mynavactivity.retrofit.network.iPostProductApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitProductBuilder;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements ApiProductAdapter.IApiProductResponseClick{

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private TextView categoryLayoutTitle;
    private RecyclerView categoryRecyclerView;


    @Override
    public void onClick(ApiProduct apiProduct) {
        Intent intent = new Intent(getActivity(), Productdetails.class);
        intent.putExtra("prodName" , apiProduct.getProductName());
        intent.putExtra("prodPrice" , ((Double) apiProduct.getPrice()));
        intent.putExtra("prodDescription" , apiProduct.getProductDescription());
        intent.putExtra("prodImage" , apiProduct.getProductImage());
        startActivity(intent);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        //configureCategoriesImageButton();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton imgCat1 = getView().findViewById(R.id.iv_cat1);
        ImageButton imgCat2 = getView().findViewById(R.id.iv_cat2);
        ImageButton imgCat3 = getView().findViewById(R.id.iv_cat3);
        ImageButton imgCat4 = getView().findViewById(R.id.iv_cat4);
        ImageButton imgCat5 = getView().findViewById(R.id.iv_cat5);
        Glide.with(this)
                .load("https://cdn11.bigcommerce.com/s-g9br3/images/stencil/2048x2048/products/1416/3890/Prometheus_Front_AMD_v2_1000x1000__46943.1610554579.jpg?c=2")
                .into(imgCat1);
        Glide.with(this)
                .load("https://m.media-amazon.com/images/I/81hoonPUdxL._SL1500_.jpg")
                .into(imgCat2);
        Glide.with(this)
                .load("https://4.imimg.com/data4/SJ/BA/MY-3018414/apple-laptop-500x500.jpg")
                .into(imgCat3);
        Glide.with(this)
                .load("https://img.gkbcdn.com/s3/p/2019-05-16/one-netbook-one-mix-3-yoga-pocket-laptop-8gb-256gb-silver-1574132616143.jpg")
                .into(imgCat4);
        Glide.with(this)
                .load("https://static.toiimg.com/thumb/msid-84267495,width-1200,height-900,resizemode-4/.jpg")
                .into(imgCat5);

        imgCat1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("category" , 1L);
            startActivity(intent);
        });
        imgCat2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("category" , 2L);
            startActivity(intent);
        });
        imgCat3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("category" , 3L);
            startActivity(intent);
        });
        imgCat4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("category" , 4L);
            startActivity(intent);
        });
        imgCat5.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("category" , 5L);
            startActivity(intent);
        });

        //localStorageApi();
        makeApi();

    }

    private void generateUserData(List<ApiProduct> userDataList) {
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
//        userDataList.add(new ApiProduct("https://media.istockphoto.com/photos/modern-laptop-with-empty-screen-on-white-background-mockup-design-picture-id1182241805?k=20&m=1182241805&s=612x612&w=0&h=NHoUPJJBdxsCsZOjOUIUzNZxxoDZrRVOJWIJRMjKM1E=" , "Lenovo Chromebook" , 209989 ));
//        userDataList.add(new ApiProduct("https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxzZWFyY2h8OHx8bGFwdG9wfGVufDB8fDB8fA%3D%3D&w=1000&q=80" , "Lenovo Chromebook" , 20990 ));
    }

    public void makeApi(){
        Retrofit retrofit = RetrofitProductBuilder.getInstance();
        iPostProductApi iPostApi = retrofit.create(iPostProductApi.class);
        Call<List<ApiProduct>> responses = iPostApi.getByCategoryId(1);
        responses.enqueue(new Callback<List<ApiProduct>>() {
            @Override
            public void onResponse(Call<List<ApiProduct>> call, Response<List<ApiProduct>> response) {
                List<ApiProduct> apiProductArrayList = new ArrayList<>();

                for(int i = 0 ; i<response.body().size();i++){
                    String productImage = response.body().get(i).getProductImage();
                    String productName = response.body().get(i).getProductName();
                    Double price = response.body().get(i).getPrice();
                    String productDescription = response.body().get(i).getProductDescription();
                    System.out.println("Price "+response.body().get(i).getPrice() + " Name " + response.body().get(i).getProductName());

                    apiProductArrayList.add(new ApiProduct(productImage,productName,price,productDescription));
                }

               // categoryLayoutTitle.setText("BEST PRODUCTS FROM DB!!!");
//                categoryRecyclerView = getView().findViewById(R.id.rv_category);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
//                categoryRecyclerView.setLayoutManager(linearLayoutManager);
//                categoryRecyclerView.setAdapter(new ApiProductAdapter(response.body(), HomeFragment.this));

                categoryRecyclerView = getView().findViewById(R.id.rv_category);
                ApiProductAdapter apiProductAdapter = new ApiProductAdapter(apiProductArrayList , HomeFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
                categoryRecyclerView.setLayoutManager(linearLayoutManager);
                categoryRecyclerView.setAdapter(apiProductAdapter);
                apiProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ApiProduct>> call, Throwable t) {
                Toast.makeText( getContext(), "Something went wrong: from Home Fragment",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void localStorageApi(){
        List<ApiProduct> apiProductArrayList = new ArrayList<>();
        generateUserData(apiProductArrayList);
        categoryLayoutTitle = getView().findViewById(R.id.tv_cat_header);
        categoryRecyclerView = getView().findViewById(R.id.rv_category);
        categoryLayoutTitle.setText("BEST PRODUCTS!!!");
        RecyclerView recyclerView = getView().findViewById(R.id.rv_category);
        ApiProductAdapter apiProductAdapter = new ApiProductAdapter(apiProductArrayList , HomeFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(apiProductAdapter);
        apiProductAdapter.notifyDataSetChanged();
    }

}