package com.example.mynavactivity.homecategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynavactivity.R;
import com.example.mynavactivity.retrofit.model.ApiCart;
import com.example.mynavactivity.retrofit.model.ApiProduct;

import java.util.List;

public class ApiCartAdapter extends RecyclerView.Adapter<ApiCartAdapter.ViewHolder>{

    //==== QTY CONTROLS =======
    int count = 0;

    private List<ApiCart> apiCartList;
    private final ApiCartAdapter.IApiCartResponseClick mCartInterface;

    public ApiCartAdapter(List<ApiCart> apiCartList, ApiCartAdapter.IApiCartResponseClick mCartInterface) {
        this.apiCartList = apiCartList;
        this.mCartInterface = mCartInterface;
    }

    @NonNull
    @Override
    public ApiCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);
        return new ApiCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiCartAdapter.ViewHolder holder, int position) {
        ApiCart cart =  apiCartList.get(position);
        Glide.with(holder.productCartImage.getContext()).load(cart.getProductImage()).placeholder(R.drawable.lap_demp).into(holder.productCartImage);
        holder.productCartName.setText(cart.getProductName());
        holder.productCartPrice.setText(cart.getPrice() +"");
        holder.productCartQuantity.setText(cart.getProductQuantity() +"");

        holder.increaseQty.setOnClickListener(v -> {
            count++;
            holder.productQty.setText(""+count);
        });
        holder.decreaseQty.setOnClickListener(v -> {
            count--;
            if(count<0)
                count=0;
            holder.productQty.setText(""+count);
        });

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCartInterface.onClick(cart);
            }
        });

    }

    public interface IApiCartResponseClick {
        void onClick(ApiCart apiCart);
    }

    @Override
    public int getItemCount() {
        return apiCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productCartImage;
        private final TextView productCartName;
        private final TextView productCartPrice;
        private final TextView productCartQuantity;

        //=======Cart Buttons============
        private final Button increaseQty;
        private final Button decreaseQty;
        private final TextView productQty;

        private final View rootView;
        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            productCartImage = itemView.findViewById(R.id.pr_image);
            productCartName = itemView.findViewById(R.id.pr_title);
            productCartPrice = itemView.findViewById(R.id.pr_price);
            productCartQuantity = itemView.findViewById(R.id.pr_qty);
            increaseQty = itemView.findViewById(R.id.increment_btn);
            decreaseQty = itemView.findViewById(R.id.decrement_btn);
            productQty = itemView.findViewById(R.id.pr_qty);
        }
    }
}
