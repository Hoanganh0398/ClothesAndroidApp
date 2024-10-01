package com.example.detai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.detai.R;
import com.example.detai.chitietsanpham;
import com.example.detai.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamNoiBat_Adapter extends RecyclerView.Adapter<SanPhamNoiBat_Adapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;


    public SanPhamNoiBat_Adapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spnoibat, parent,false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SanPhamNoiBat_Adapter.MyViewHolder holder, int position) {
        if(holder instanceof SanPhamNoiBat_Adapter.MyViewHolder){
            SanPhamNoiBat_Adapter.MyViewHolder myViewHolder = (SanPhamNoiBat_Adapter.MyViewHolder) holder;
            SanPhamMoi sanPhamMoi = array.get(position);
            myViewHolder.txtten.setText(sanPhamMoi.getTensp().trim());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.txtgia.setText("Giá: "+decimalFormat.format(sanPhamMoi.getPrice())+"đ");
            int drawableResourceId = context.getResources().getIdentifier(sanPhamMoi.getHinhanh(), "drawable", context.getPackageName());
            Glide.with(context).load(drawableResourceId).into(holder.imgsp);
            myViewHolder.imgsp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, chitietsanpham.class);
                    intent.putExtra("chitiet", sanPhamMoi);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtgia, txtten;
        ImageView imgsp;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.itemsp_ten);
            txtgia = itemView.findViewById(R.id.itemsp_gia);
            imgsp = itemView.findViewById(R.id.itemsp_img);
        }
    }
}
