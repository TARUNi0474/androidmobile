package com.example.mynavactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class HomePage extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.user_auth", Context.MODE_PRIVATE);

//        Bundle frag_bundle = getIntent().getExtras();
//        if(frag_bundle != null){
//            frag_bundle.getString();
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button buttonLogout = findViewById(R.id.bt_logout);
        Button buttonGLogout = findViewById(R.id.bt_glogout);
        TextView GpersonName = findViewById(R.id.textViewName);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        buttonGLogout.setOnClickListener(v->{
            signOut();
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
            GpersonName.setText(personName);
        }

        buttonLogout.setOnClickListener(v -> {
            Intent i = new Intent(HomePage.this, UserLogin.class);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Login", false);
            editor.apply();
            startActivity(i);
            finish();
        });



    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i = new Intent(HomePage.this, UserLogin.class);
                        Toast.makeText(HomePage.this , "SignOut! ", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();
                    }
                });
    }


}