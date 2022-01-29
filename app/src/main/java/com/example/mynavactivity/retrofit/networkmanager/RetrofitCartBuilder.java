package com.example.mynavactivity.retrofit.networkmanager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCartBuilder {
    private static Retrofit instance;

    private RetrofitCartBuilder(){
    }
    public static Retrofit getInstance(){
        if(instance == null){
            synchronized (RetrofitCartBuilder.class){
                if(instance == null){
                    instance = new Retrofit.Builder().baseUrl("http://10.177.1.208:8084")
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }
}
