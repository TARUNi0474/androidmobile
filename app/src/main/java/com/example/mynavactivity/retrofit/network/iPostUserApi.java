package com.example.mynavactivity.retrofit.network;

import com.example.mynavactivity.retrofit.dto.UserDto;
import com.example.mynavactivity.retrofit.model.ApiProduct;
import com.example.mynavactivity.retrofit.model.ApiProfile;
import com.example.mynavactivity.retrofit.model.ApiUser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface iPostUserApi {

    @POST("/user")
    Call<Void> register(@Body UserDto userDto);

    @POST("/user/validate")
    Call<JsonElement> validation(@Body UserDto userDto);

    @GET("/user/{email}")
    Call<UserDto> getProfile(@Path("email") String email);
}

