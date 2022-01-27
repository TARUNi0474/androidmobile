package com.example.mynavactivity.ui.support;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.mynavactivity.R;

public class SupportFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_support, container, false);
        Button contact_btn = root.findViewById(R.id.bt_contact);
        contact_btn.setOnClickListener(v -> {
            Toast.makeText(getActivity(),"Feedback Recorded" , Toast.LENGTH_SHORT).show();
        });
        return root;
    }

}