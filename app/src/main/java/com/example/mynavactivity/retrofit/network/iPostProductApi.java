package com.example.mynavactivity.retrofit.network;

import com.example.mynavactivity.retrofit.model.ApiProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface iPostProductApi {

    @GET("/products/getByCId/{id}")
    Call<List<ApiProduct>> getByCategoryId(@Path("id") long id);

}
