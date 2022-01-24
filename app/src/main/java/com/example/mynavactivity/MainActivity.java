package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //=====Splash Screen===========

        ImageView gifImage = findViewById(R.id.imageView_splash);
        Glide.with(this).asGif()
                .load("https://i.pinimg.com/originals/24/5d/9d/245d9d30b0ba45ce3b9768b8e5b52b2b.gif")
                .into(gifImage);

       // SharedPreferences sharedPreferences = getSharedPreferences("com.example.mynavactivity" , Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Boolean temp = sharedPreferences.getBoolean("Login" , false);

                Intent i = new Intent(MainActivity.this,
                        UserLogin.class);

//                if(temp){
//                    i = new Intent(MainActivity.this,
//                            HomePage.class);
//
//                }else
//                {
//                    i = new Intent(MainActivity.this,
//                            UserLogin.class);
//
//                }
//                startActivity(i);

                //Intent is used to switch from one activity to another.

                //invoke the SecondActivity.
                startActivity(i);
                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}