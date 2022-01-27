package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavactivity.retrofit.dto.UserDto;
import com.example.mynavactivity.retrofit.model.ApiProduct;
import com.example.mynavactivity.retrofit.model.ApiUser;
import com.example.mynavactivity.retrofit.network.iPostUserApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitProductBuilder;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitUserBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserSignup extends AppCompatActivity {
    boolean isAllFieldsChecked = false;
    Button buttonLogin,buttonSignUp;
    EditText etUserName,etPassword,etConfirmPassword,etEmail,etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        buttonLogin = findViewById(R.id.bt_login);
        buttonSignUp = findViewById(R.id.bt_signIt);

        etUserName = findViewById(R.id.tv_signusername);
        etPassword = findViewById(R.id.tv_signpass);
        etConfirmPassword = findViewById(R.id.tv_signconfirmpass);
        etEmail = findViewById(R.id.tv_signemail);
        etAddress = findViewById(R.id.tv_signaddress);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    makeApi(createRequest());
                }
            }

            private boolean CheckAllFields() {
                String emailToText = etEmail.getText().toString();

                if (etUserName.length() == 0) {
                    etUserName.setError("This field is required");
                    return false;
                }
                if(!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))){
                    etPassword.setError("Password Don't Match!");
                    return false;
                }

                if (etPassword.length() == 0) {
                    etPassword.setError("Password is required");
                    return false;

                } else if (etPassword.length() < 8) {
                    etPassword.setError("Password must be minimum 8 characters");
                    return false;
                }
                if (!(!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches())) {
                    etEmail.setError("This field is invalid");
                    return false;
                }
                // after all validation return true.
                return true;
            }

        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSignup.this, UserLogin.class);
                startActivity(i);
            }
        });
    }

    public UserDto createRequest(){
        UserDto userDto = new UserDto();
        userDto.setName(etUserName.getText().toString());
        userDto.setPassword(etPassword.getText().toString());
        userDto.setEmail(etEmail.getText().toString());
        userDto.setAddress(etAddress.getText().toString());
        return userDto;
    }

    public void makeApi(UserDto userDto){
        Retrofit retrofit = RetrofitUserBuilder.getInstance();
        iPostUserApi iPostApi = retrofit.create(iPostUserApi.class);
        Call<Void> responses = iPostApi.register(userDto);
        responses.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UserSignup.this,"Successfully Signed in", Toast.LENGTH_SHORT).show();
//                System.out.println(response);
                Intent i = new Intent(UserSignup.this, UserLogin.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UserSignup.this,"Failed To Register " + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}