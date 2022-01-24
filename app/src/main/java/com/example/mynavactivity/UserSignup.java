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

public class UserSignup extends AppCompatActivity {
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        Button buttonLogin = findViewById(R.id.bt_login);
        Button buttonSignUp = findViewById(R.id.bt_signIt);

        // register all the EditText fields with their IDs.
        EditText etUserName = findViewById(R.id.tv_signusername);
        EditText etPassword = findViewById(R.id.tv_signpass);
        EditText etConfirmPassword = findViewById(R.id.tv_signconfirmpass);
        EditText etEmail = findViewById(R.id.tv_signemail);

       // SharedPreferences sharedPreferences = getSharedPreferences("com.example.mynavactivity" , Context.MODE_PRIVATE);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    Intent i = new Intent(UserSignup.this, UserLogin.class);
                    startActivity(i);
                    finish();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(etUserName.getText().toString() , etPassword.getText().toString());
//                    editor.apply();
//                    Intent i = new Intent(UserSignup.this, UserLogin.class);
//                    startActivity(i);
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
}