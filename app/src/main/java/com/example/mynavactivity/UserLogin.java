package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavactivity.retrofit.dto.UserDto;
import com.example.mynavactivity.retrofit.network.iPostUserApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitUserBuilder;
import com.example.mynavactivity.ui.profile.ProfileFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserLogin extends AppCompatActivity {

    boolean isAllFieldsChecked = false;
    Button buttonLogin,buttonSignUp;
    EditText etEmail,etPassword;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
//    SharedPreferences sharedPreferences = getSharedPreferences("com.example.mynavactivity", Context.MODE_PRIVATE);
//SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("lEmail", etEmail.getText().toString());
//        editor.apply();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //=======GOOGLE SIGN-IN LOGIC=============//

        // Configure sign-in to request the user's ID, email address, and basic profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //======APP LOGIC============//
        buttonLogin = findViewById(R.id.bt_login);
        buttonSignUp = findViewById(R.id.bt_signup);

        // register all the EditText fields with their IDs.
        etEmail = findViewById(R.id.tv_email);
        etPassword = findViewById(R.id.tv_pass);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();
                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    makeApi(createLoginRequest());

                }
            }

            private boolean CheckAllFields() {
                if (etEmail.length() == 0) {
                    etEmail.setError("This field is required");
                    return false;
                }

                if (etPassword.length() == 0) {
                    etPassword.setError("Password is required");
                    return false;
                } else if (etPassword.length() < 8) {
                    etPassword.setError("Password must be minimum 8 characters");
                    return false;
                }

                // after all validation return true.
                return true;
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserLogin.this, UserSignup.class);
                startActivity(i);
            }
        });
    }

    public UserDto createLoginRequest(){
        UserDto userDto = new UserDto();
        userDto.setEmail(etEmail.getText().toString());
        userDto.setPassword(etPassword.getText().toString());
        return userDto;
    }

    public void makeApi(UserDto userDto){
        Retrofit retrofit = RetrofitUserBuilder.getInstance();
        iPostUserApi iPostApi = retrofit.create(iPostUserApi.class);
        Call<JsonElement> responses = iPostApi.validation(userDto);
        responses.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonObject jsonObject = response.body().getAsJsonObject();
                int id = Integer.parseInt(String.valueOf(jsonObject.get("Status")));
                if(id==500)
                    Toast.makeText(UserLogin.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                else if(id==400)
                    Toast.makeText(UserLogin.this,"Email is incorrect",Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(UserLogin.this,"Successfully Logged In!",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", userDto.getEmail());
                    editor.apply();
                    Intent i = new Intent(UserLogin.this, HomePageNavActivity.class);
                    i.putExtra("userEmail" , userDto.getEmail());
                    startActivity(i);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(UserLogin.this,"Failed To Register " + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(this , "User Email : " + personEmail , Toast.LENGTH_SHORT).show();

            }
            startActivity(new Intent(UserLogin.this , HomePageNavActivity.class));

            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Message", e.toString());
        }
    }
}