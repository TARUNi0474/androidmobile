package com.example.mynavactivity.retrofit.networkmanager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUserBuilder {
    private static Retrofit instance;

    public RetrofitUserBuilder() {
    }
    public static Retrofit getInstance(){
        if(instance == null){
            synchronized (RetrofitUserBuilder.class){
                if(instance == null){
                    instance = new Retrofit.Builder().baseUrl("http://10.177.1.208:8091/")
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }

}
