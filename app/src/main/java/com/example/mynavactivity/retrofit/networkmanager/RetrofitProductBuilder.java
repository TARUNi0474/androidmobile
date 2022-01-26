package com.example.mynavactivity.retrofit.networkmanager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProductBuilder {
    private static Retrofit instance;

    private RetrofitProductBuilder(){
    }
    public static Retrofit getInstance(){
        if(instance == null){
            synchronized (RetrofitProductBuilder.class){
                if(instance == null){
                    instance = new Retrofit.Builder().baseUrl("http://10.177.1.208:8080/")
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }
}
