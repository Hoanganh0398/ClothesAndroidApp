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
import com.example.detai.Interface.ItemClickListener;
import com.example.detai.R;
import com.example.detai.chitietsanpham;
import com.example.detai.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class SpAosomi_Adapter extends RecyclerView.Adapter<SpAosomi_Adapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public SpAosomi_Adapter(Context context, List array) {
        this.context = context;
        this.array = array;
    }

 //Gán layout để xuất sp
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp,parent,false);
        return new MyViewHolder(view);
    }
//Gọi sản phẩm từ database qua ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpAosomi_Adapter.MyViewHolder myViewHolder = (SpAosomi_Adapter.MyViewHolder) holder;
        SanPhamMoi sanPham = array.get(position);
        holder.txtten.setText(sanPham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: "+decimalFormat.format(sanPham.getPrice())+"đ");
        int drawableResourceId = context.getResources().getIdentifier(sanPham.getHinhanh(), "drawable", context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.imgsp);
        myViewHolder.setItemClickListener(new ItemClickListener() {
            //Chuyển activity sang chi tiết sp
            @Override
            public void onClick(View view, int pos, boolean islongClick) {
                if(!islongClick){
                    Intent intent = new Intent(context, chitietsanpham.class);
                    intent.putExtra("chitiet", sanPham);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtgia, txtten;
        ImageView imgsp;
        private ItemClickListener itemClickListener;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.itemsp_ten);
            txtgia = itemView.findViewById(R.id.itemsp_gia);
            imgsp = itemView.findViewById(R.id.itemsp_img);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
