package com.example.detai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.detai.Interface.ItemClickListener;
import com.example.detai.R;
import com.example.detai.chitietsanpham;
import com.example.detai.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamMoi_Adapter extends RecyclerView.Adapter<SanPhamMoi_Adapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public SanPhamMoi_Adapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }
    //Gán layout để xuất sp
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spmoi, parent,false);
        return new MyViewHolder(item);
    }
    //Gọi sản phẩm từ database qua ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            SanPhamMoi sanPhamMoi = array.get(position);
            myViewHolder.txtten.setText(sanPhamMoi.getTensp().trim());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.txtgia.setText("Giá: "+decimalFormat.format(sanPhamMoi.getPrice())+"đ");
            int drawableResourceId = context.getResources().getIdentifier(sanPhamMoi.getHinhanh(), "drawable", context.getPackageName());
            Glide.with(context).load(drawableResourceId).into(holder.imgsp);
            myViewHolder.setItemClickListener(new ItemClickListener() {
                //Chuyển activity sang chi tiết sp
                @Override
                public void onClick(View view, int pos, boolean islongClick) {
                    if(!islongClick){
                        Intent intent = new Intent(context, chitietsanpham.class);
                        intent.putExtra("chitiet", sanPhamMoi);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }// else {
          //  LoadingViewHolder
       // }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener itemClickListener;
       TextView txtgia, txtten;
       ImageView imgsp;
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
