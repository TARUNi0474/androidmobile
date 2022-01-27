package com.example.mynavactivity.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mynavactivity.R;
import com.example.mynavactivity.UserLogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LogoutFragment extends Fragment {
    GoogleSignInClient mGoogleSignInClient;
    String personName;
//    SharedPreferences sharedPreferences = getSharedPreferences("com.example.user_auth", Context.MODE_PRIVATE);
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        Button buttonLogout = root.findViewById(R.id.bt_logout);
        Button buttonGLogout = root.findViewById(R.id.bt_glogout);
        TextView GpersonName = root.findViewById(R.id.tv_placeholder);
        GpersonName.setText("We'll meet Again Right?");
//        ImageView imgLogOut = root.findViewById(R.id.imageView3);
//        Glide.with(this)
//                .load("https://www.vhv.rs/dpng/d/165-1655121_transparent-exit-door-png-person-walking-through-door.png")
//                .into(imgLogOut);
        buttonLogout.setText("Yes , Log Me Out");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        buttonGLogout.setOnClickListener(v->{
            signOut();
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//            GpersonName.setText("Oh no! you're leaving...\nWe'll meet Again?");
        }

        buttonLogout.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), UserLogin.class);
            Toast.makeText(getActivity(),"Logging " + personName + " out!" , Toast.LENGTH_SHORT).show();
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("Login", false);
//            editor.apply();
//            startActivity(i);
//            finish();
        });

        return root;
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i = new Intent(getActivity(), UserLogin.class);
                        Toast.makeText(getActivity(),"Logging " + personName + " out!" , Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });
    }

}