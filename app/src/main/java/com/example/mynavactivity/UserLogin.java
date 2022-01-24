package com.example.mynavactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class UserLogin extends AppCompatActivity {

    boolean isAllFieldsChecked = false;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;

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
        Button buttonLogin = findViewById(R.id.bt_login);
        Button buttonSignUp = findViewById(R.id.bt_signup);

        // register all the EditText fields with their IDs.
        EditText etUserName = findViewById(R.id.tv_username);
        EditText etPassword = findViewById(R.id.tv_pass);
        //SharedPreferences sharedPreferences = getSharedPreferences("com.example.mynavactivity", Context.MODE_PRIVATE);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    Intent i = new Intent(UserLogin.this, HomePageNavActivity.class);
                    startActivity(i);
                        finish();
//                    String temp = sharedPreferences.getString(etUserName.getText().toString(), "Default");
//                    if (!(temp.equals(etPassword.getText().toString()))) {
//                        etPassword.setError("Invalid Password!");
//                    } else {
//                        Intent i = new Intent(UserLogin.this, HomePage.class);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean("Login", true);
//                        editor.apply();
//                        startActivity(i);
//                        finish();
//                    }
                }
            }

            private boolean CheckAllFields() {
                if (etUserName.length() == 0) {
                    etUserName.setError("This field is required");
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