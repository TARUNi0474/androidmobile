package com.example.mynavactivity.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynavactivity.R;
import com.example.mynavactivity.homecategory.ApiOrderAdapter;
import com.example.mynavactivity.retrofit.model.ApiOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements ApiOrderAdapter.IApiOrderResponseClick{

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }
    @Override
    public void onClick(ApiOrder apiOrder) {
        Toast.makeText(getActivity(),"clicked",Toast.LENGTH_SHORT).show();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ApiOrder> apiOrderArrayList = new ArrayList<>();
        generateUserData(apiOrderArrayList);
        RecyclerView recyclerView = getView().findViewById(R.id.rv_order);
        ApiOrderAdapter apiOrderAdapter = new ApiOrderAdapter(apiOrderArrayList , OrderFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(apiOrderAdapter);
        apiOrderAdapter.notifyDataSetChanged();
    }

    private void generateUserData(List<ApiOrder> userDataList) {
        userDataList.add(new ApiOrder(1288 , "12/02/2020" , 20990 ));
        userDataList.add(new ApiOrder(1232 , "12/02/2020" , 20990 ));
        userDataList.add(new ApiOrder(1390 , "12/02/2020" , 20990 ));
        userDataList.add(new ApiOrder(2987 , "12/02/2020" , 20990 ));
        userDataList.add(new ApiOrder(2209 , "12/02/2020" , 20990 ));
        userDataList.add(new ApiOrder(1134 , "12/02/2020" , 20990 ));


    }
}