package com.example.mynavactivity.homecategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynavactivity.R;
import com.example.mynavactivity.retrofit.model.ApiProduct;

import java.util.List;

public class ApiProductAdapter extends RecyclerView.Adapter<ApiProductAdapter.ViewHolder>{

    private List<ApiProduct> apiProductList;
    private final IApiProductResponseClick mProductInterface;

    public ApiProductAdapter(List<ApiProduct> apiProductList, IApiProductResponseClick mProductInterface) {
        this.apiProductList = apiProductList;
        this.mProductInterface = mProductInterface;
    }

    @NonNull
    @Override
    public ApiProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_category_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiProductAdapter.ViewHolder holder, int position) {
        ApiProduct product =  apiProductList.get(position);
        Glide.with(holder.productImage.getContext()).load(product.getProductImage()).placeholder(R.drawable.lap_demp).into(holder.productImage);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(product.getPrice() +"");
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductInterface.onClick(product);
            }
        });
    }

    public interface IApiProductResponseClick {
        void onClick(ApiProduct apiProduct);
    }

    @Override
    public int getItemCount() {
        return apiProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView productImage;
        private final TextView productName;
        private final TextView productPrice;
        private final View rootView;
        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            productImage = itemView.findViewById(R.id.bt_cat_item_image);
            productName = itemView.findViewById(R.id.tv_item_name);
            productPrice = itemView.findViewById(R.id.tv_item_price);
        }
    }
}
