package com.example.mynavactivity.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mynavactivity.R;
import com.example.mynavactivity.UserLogin;
import com.example.mynavactivity.homecategory.ApiProductAdapter;
import com.example.mynavactivity.retrofit.dto.UserDto;
import com.example.mynavactivity.retrofit.model.ApiProduct;
import com.example.mynavactivity.retrofit.model.ApiProfile;
import com.example.mynavactivity.retrofit.model.ApiUser;
import com.example.mynavactivity.retrofit.network.iPostProductApi;
import com.example.mynavactivity.retrofit.network.iPostUserApi;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitProductBuilder;
import com.example.mynavactivity.retrofit.networkmanager.RetrofitUserBuilder;
import com.example.mynavactivity.ui.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment{
    GoogleSignInClient mGoogleSignInClient;
    TextView userProfileEmail , userProfileName , userProfileAddress;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonLogout = getView().findViewById(R.id.bt_logout);
        userProfileName = getView().findViewById(R.id.profName);
        userProfileAddress= getView().findViewById(R.id.profAddress);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myKey", Context.MODE_PRIVATE);
        userProfileEmail = getView().findViewById(R.id.profEmail);
        userProfileEmail.setText(sharedPreferences.getString("value","userName"));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
            userProfileEmail.setText(personName);
            userProfileName.setText(personEmail);
            TextView demo_add = getView().findViewById(R.id.tv_add);
            demo_add.setText("");
            userProfileAddress.setVisibility(userProfileAddress.INVISIBLE);
        }else{
            makeApi();
        }

        buttonLogout.setOnClickListener(v -> {
            signOut();
            Toast.makeText(getActivity(),"Logging you out!" , Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent i = new Intent(getActivity(), UserLogin.class);
            startActivity(i);
        });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i = new Intent(getActivity(), UserLogin.class);
                        //Toast.makeText(getActivity(),"Logging " + personName + " out!" , Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });
    }

    public void makeApi(){
        Retrofit retrofit = RetrofitUserBuilder.getInstance();
        iPostUserApi iPostApi = retrofit.create(iPostUserApi.class);
        Call<UserDto> responses = iPostApi.getProfile(userProfileEmail.getText().toString());
        responses.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                userProfileName.setText(response.body().getName());
                userProfileAddress.setText(response.body().getAddress());
                 System.out.println("Name " + response.body().getName() + "Address " + response.body().getAddress());
                System.out.println("Email " + userProfileEmail.getText().toString());
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {
                Toast.makeText(getActivity(),"Failed To Show " + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}