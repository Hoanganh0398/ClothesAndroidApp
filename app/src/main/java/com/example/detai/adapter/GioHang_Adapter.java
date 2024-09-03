package com.example.detai.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.detai.EventBus.TinhTongEvent;
import com.example.detai.Interface.IIamgeClickListener;
import com.example.detai.R;
import com.example.detai.Utils.Utils;
import com.example.detai.model.GioHang;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import io.paperdb.Paper;

public class GioHang_Adapter extends RecyclerView.Adapter<GioHang_Adapter.MyViewHolder>{
    Context context;
    List<GioHang> gioHangList;

    public GioHang_Adapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.item_giohang_tensp.setText(gioHang.getTensp());
        holder.item_giohang_soluong.setText(gioHang.getSoluong()+" ");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_giohang_gia.setText("Giá: "+decimalFormat.format(gioHang.getPrice())+"đ");
        long gia = gioHang.getSoluong() * gioHang.getPrice();
        holder.item_giohang_giasp2.setText("Tổng: "+decimalFormat.format(gia)+"đ");
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //Xử lí sự kiện của checkbox giỏ hàng
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Utils.manggiohang.get(holder.getAdapterPosition()).setChecked(true);
                    if (!Utils.mangmuahang.contains(gioHang)){
                        Utils.mangmuahang.add(gioHang);
                    }
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                } else {
                    Utils.manggiohang.get(holder.getAdapterPosition()).setChecked(true);
                    for (int i = 0; i<Utils.mangmuahang.size(); i++){
                        if(Utils.mangmuahang.get(i).getId() == gioHang.getId()){
                            Utils.mangmuahang.remove(i);
                            EventBus.getDefault().postSticky(new TinhTongEvent());
                        }
                    }
                }
            }
        });
        int drawableResourceId = context.getResources().getIdentifier(gioHang.getHinhanh(), "drawable", context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.item_giohang_img);
        holder.checkBox.setChecked(gioHang.isChecked());
        holder.setListener(new IIamgeClickListener() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if (giatri == 1){
                    if (gioHangList.get(pos).getSoluong() > 1) {
                        int soluongmoi = gioHangList.get(pos).getSoluong() - 1;
                        gioHangList.get(pos).setSoluong(soluongmoi);
                        holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+" ");
                        long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getPrice();
                        holder.item_giohang_giasp2.setText("Tổng: "+decimalFormat.format(gia)+"đ");
                        EventBus.getDefault().postSticky(new TinhTongEvent());
                    } else if (gioHangList.get(pos).getSoluong() == 1){ //Thông báo
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn muốn xóa sản phẩm khỏi giỏ hàng?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Utils.mangmuahang.remove(gioHang);
                                Utils.manggiohang.remove(pos);
                                Paper.book().write("giohang",Utils.manggiohang);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                    }
                } else if (giatri == 2){
                        int soluongmoi = gioHangList.get(pos).getSoluong() + 1;
                        gioHangList.get(pos).setSoluong(soluongmoi);
                    holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+" ");
                    long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getPrice();
                    holder.item_giohang_giasp2.setText("Tổng: "+decimalFormat.format(gia)+"đ");
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_img, imgtru, imgcong;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;
        CheckBox checkBox;
        IIamgeClickListener listener;
        public MyViewHolder(View itemView) {
            super(itemView);
            item_giohang_img = itemView.findViewById(R.id.item_giohang_img);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia= itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            imgcong = itemView.findViewById(R.id.item_giohang_cong);
            imgtru = itemView.findViewById(R.id.item_giohang_tru);
            checkBox = itemView.findViewById(R.id.item_giohang_ck);

            imgcong.setOnClickListener(this);
            imgtru.setOnClickListener(this);
        }
        public void setListener(IIamgeClickListener listener){
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if (view == imgtru){
                listener.onImageClick(view, getAdapterPosition(),1);
            } else if (view == imgcong){
                listener.onImageClick(view, getAdapterPosition(),2);
            }
        }
    }
}