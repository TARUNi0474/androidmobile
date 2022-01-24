package com.example.mynavactivity.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynavactivity.R;
import com.example.mynavactivity.homecategory.ApiProductAdapter;
import com.example.mynavactivity.homecategory.model.ApiProduct;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ApiProductAdapter.IApiProductResponseClick{

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    // ===== Horizontal Category ========= //
    private TextView categoryLayoutTitle;
    private RecyclerView categoryRecyclerView;

    @Override
    public void onClick(ApiProduct apiProduct) {
       // Log.d("yoooo", apiProduct.getProductTitle()+ " was clicked");
        Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "Click event for " + apiProduct.getProductTitle(), Toast.LENGTH_SHORT).show();
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

        List<ApiProduct> apiProductArrayList = new ArrayList<>();

        generateUserData(apiProductArrayList);
        categoryLayoutTitle = getView().findViewById(R.id.tv_cat_header);
        categoryRecyclerView = getView().findViewById(R.id.rv_category);
        categoryLayoutTitle.setText("BEST PRODUCTS!");

        RecyclerView recyclerView = getView().findViewById(R.id.rv_category);
        ApiProductAdapter apiProductAdapter = new ApiProductAdapter(apiProductArrayList , HomeFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(apiProductAdapter);
        apiProductAdapter.notifyDataSetChanged();
    }
    private void generateUserData(List<ApiProduct> userDataList) {
        userDataList.add(new ApiProduct(R.drawable.ic_logout , "Lenovo Chromebook" , "₹20,990.00" ));
        userDataList.add(new ApiProduct(R.drawable.ic_menu_camera , "Lenovo Chromebook" , "₹20,990.00" ));
        userDataList.add(new ApiProduct(R.drawable.ic_order , "Lenovo Chromebook" , "₹20,990.00" ));
        userDataList.add(new ApiProduct(R.drawable.ic_cart , "Lenovo Chromebook" , "₹20,990.00" ));
        userDataList.add(new ApiProduct(R.drawable.ic_menu_gallery , "Lenovo Chromebook" , "₹20,990.00" ));
    }

}