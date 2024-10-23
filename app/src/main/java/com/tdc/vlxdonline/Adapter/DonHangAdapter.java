package com.tdc.vlxdonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tdc.vlxdonline.Model.TaoDonHang;
import com.tdc.vlxdonline.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.UserViewHolder> {
    private Context mContext;
    private List<TaoDonHang> mListDonHang;
//    ArrayList<DonHang> arrayList = new ArrayList<>();

    public DonHangAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<TaoDonHang> list){
        this.mListDonHang = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TaoDonHang donHang = mListDonHang.get(position);
        if (donHang == null){
            return;
        }
        holder.imgDonHang.setImageResource(donHang.getResourceImage());
        holder.tvName.setText(donHang.getName());
//        holder.imgDonHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "Country"+position, Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext,TaoDonHang.class);
//                intent.putExtra("flag",arrayList.get(position).getResourceImage());
//                intent.putExtra("name",arrayList.get(position).getName());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (mListDonHang != null){
            return mListDonHang.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgDonHang;
        private TextView tvName;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDonHang = itemView.findViewById(R.id.imgItem);
            tvName = itemView.findViewById(R.id.tvNameItem);
        }
    }
}
