package com.tdc.vlxdonline.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdc.vlxdonline.Model.Products;
import com.tdc.vlxdonline.R;
import com.tdc.vlxdonline.databinding.ItemProductBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Activity context;
    private ArrayList<Products> data;
    private int displayButtonBuy;
    private OnItemProductClickListener onItemProductClickListener;

    public void setOnItemProductClickListener(OnItemProductClickListener onItemProductClickListener) {
        this.onItemProductClickListener = onItemProductClickListener;
    }

    public ProductAdapter(Activity context, ArrayList<Products> data, int displayButtonBuy) {
        this.context = context;
        this.data = data;
        this.displayButtonBuy = displayButtonBuy;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductHolder(ItemProductBinding.inflate(context.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Products product = data.get(position);
        holder.binding.imgItem.setImageResource(product.getAnh());
        holder.binding.tvNameItem.setText(product.getTen());
        StringBuilder chuoi = new StringBuilder(String.valueOf(product.getGia()));
        if (chuoi.length() > 3) {
            int dem = 0;
            int doDai = chuoi.length() - 1;
            for (int i = doDai; i > 0; i--) {
                dem = dem + 1;
                if (dem == 3) {
                    chuoi.insert(i, '.');
                    dem = 0;
                }
            }
        }
        holder.binding.tvPriceItem.setText(chuoi);
        if (product.getSoSao() > 0) {
            if (product.getSoSao() < 4.8) {
                holder.binding.star5.setImageResource(R.drawable.star_disable);
            }
            if (product.getSoSao() < 3.8) {
                holder.binding.star4.setImageResource(R.drawable.star_disable);
            }
            if (product.getSoSao() < 2.8) {
                holder.binding.star3.setImageResource(R.drawable.star_disable);
            }
            if (product.getSoSao() < 1.8) {
                holder.binding.star2.setImageResource(R.drawable.star_disable);
            }
        }
        holder.binding.btnBuy.setVisibility(displayButtonBuy);
        final int pos = position;
        holder.position = pos;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ItemProductBinding binding;
        int position;
        public ProductHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemProductClickListener != null) {
                        onItemProductClickListener.OnItemClick(v, position);
                    }
                }
            });
            binding.btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemProductClickListener != null) {
                        onItemProductClickListener.OnBtnBuyClick(v, position);
                    }
                }
            });
        }
    }

    public interface OnItemProductClickListener{
        void OnItemClick(View view, int position);
        void OnBtnBuyClick(View view, int position);
    }
}
