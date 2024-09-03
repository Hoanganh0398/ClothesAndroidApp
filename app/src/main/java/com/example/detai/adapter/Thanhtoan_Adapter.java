package com.example.detai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.detai.R;
import com.example.detai.model.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class Thanhtoan_Adapter extends RecyclerView.Adapter<Thanhtoan_Adapter.MyViewHolder>{
    Context context;
    List<GioHang> gioHangList;

    public Thanhtoan_Adapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhtoan, parent,false);
        return new Thanhtoan_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.item_thanhtoan_tensp.setText(gioHang.getTensp());
        holder.item_thanhtoan_soluong.setText("SL: "+gioHang.getSoluong()+" ");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_thanhtoan_gia.setText("Giá: "+decimalFormat.format(gioHang.getPrice())+"đ");
        long gia = gioHang.getSoluong() * gioHang.getPrice();
        holder.item_thanhtoan_giasp2.setText("Tổng: "+decimalFormat.format(gia)+"đ");
        int drawableResourceId = context.getResources().getIdentifier(gioHang.getHinhanh(), "drawable", context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.item_thanhtoan_img);

    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView item_thanhtoan_img;
        TextView item_thanhtoan_tensp, item_thanhtoan_gia, item_thanhtoan_soluong, item_thanhtoan_giasp2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_thanhtoan_img = itemView.findViewById(R.id.item_thanhtoan_img);
            item_thanhtoan_tensp = itemView.findViewById(R.id.item_thanhtoan_tensp);
            item_thanhtoan_gia= itemView.findViewById(R.id.item_thanhtoan_gia);
            item_thanhtoan_soluong = itemView.findViewById(R.id.item_thanhtoan_soluong);
            item_thanhtoan_giasp2 = itemView.findViewById(R.id.item_thanhtoan_giasp2);
        }
    }
}
