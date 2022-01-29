package com.example.mynavactivity.retrofit.network;

import com.example.mynavactivity.retrofit.dto.CartDto;
import com.example.mynavactivity.retrofit.dto.CartItemDto;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface iPostCartApi {

    @GET("/cartList/getItems/{email}")
    Call<CartItemDto> getAllItems(@Path("email") String email);

    @POST("/cartList/add")
    Call<Void> addItem(@Body CartDto cartDto);

    @HTTP(method = "DELETE" , path="/cartList/delete" , hasBody = true)
    Call<Void> delete(@Body CartDto cartDto);
}
