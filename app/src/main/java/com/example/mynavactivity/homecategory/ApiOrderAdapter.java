package com.example.mynavactivity.homecategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynavactivity.R;
import com.example.mynavactivity.retrofit.model.ApiOrder;
import java.util.List;

public class ApiOrderAdapter extends RecyclerView.Adapter<ApiOrderAdapter.ViewHolder>{
    private List<ApiOrder> apiOrderList;
    private final ApiOrderAdapter.IApiOrderResponseClick mOrderInterface;

    public ApiOrderAdapter(List<ApiOrder> apiOrderList, IApiOrderResponseClick mOrderInterface) {
        this.apiOrderList = apiOrderList;
        this.mOrderInterface = mOrderInterface;
    }

    public interface IApiOrderResponseClick {
        void onClick(ApiOrder apiOrder);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_singleview, parent, false);
        return new ApiOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiOrder order =  apiOrderList.get(position);
        holder.orderId.setText(order.getOrderId() + "");
        holder.dateOfPurchase.setText(order.getDateOfPurchase());
        holder.amount.setText(order.getAmount() + "");


        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderInterface.onClick(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return apiOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderId;
        private final TextView dateOfPurchase;
        private final TextView amount;
        private final View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            orderId = itemView.findViewById(R.id.tv_orderId);
            dateOfPurchase = itemView.findViewById(R.id.tv_dop);
            amount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
