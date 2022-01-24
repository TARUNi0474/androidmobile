package com.example.mynavactivity.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mynavactivity.HomePage;
import com.example.mynavactivity.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class LogoutFragment extends Fragment {
    GoogleSignInClient mGoogleSignInClient;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        final TextView textView = root.findViewById(R.id.text_logout);
        //textView.setText("This is Logout Layout!");
        //Button buttonGLogout = getView().findViewById(R.id.bt_glogout);
        // GpersonName = getView().findViewById(R.id.textViewName);
        Button Gbtn = (Button) root.findViewById(R.id.bt_google_logout);

        Gbtn.setOnClickListener(v -> {
            Intent i = new Intent(getActivity() , HomePage.class);
            startActivity(i);
        });
        return root;
    }


}