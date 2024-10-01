package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.detai.Utils.Utils;
import com.example.detai.model.GioHang;
import com.example.detai.model.SanPhamMoi;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.Objects;

import io.paperdb.Paper;

public class chitietsanpham extends AppCompatActivity {
    TextView tensp;
    TextView giasp;
    TextView mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    NotificationBadge badge;
    Toolbar toolbarchitiet;

    SanPhamMoi sanPhamMoi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham);
        initView();
        initData();
        initControl();
    }

    private void initControl() {
        setSupportActionBar(toolbarchitiet);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGiohang();
                Paper.book().write("giohang",Utils.manggiohang);
            }
        });
    }

    private void themGiohang() {
        if (Utils.manggiohang.size()>0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i < Utils.manggiohang.size(); i++){
                if (Utils.manggiohang.get(i).getId() == sanPhamMoi.getId()){
                    Utils.manggiohang.get(i).setSoluong(soluong + Utils.manggiohang.get(i).getSoluong());

                    long gia = Long.parseLong(String.valueOf(sanPhamMoi.getPrice()));
                    Utils.manggiohang.get(i).setPrice((int) gia);
                    flag = true;
                }
            }
            if (flag == false){
                long gia = Long.parseLong(String.valueOf(sanPhamMoi.getPrice()));
                GioHang gioHang = new GioHang();
                gioHang.setPrice((int) gia);
                gioHang.setSoluong(soluong);
                gioHang.setId(sanPhamMoi.getId());
                gioHang.setTensp(sanPhamMoi.getTensp());
                gioHang.setMota(sanPhamMoi.getMota());
                gioHang.setHinhanh(sanPhamMoi.getHinhanh());
                Utils.manggiohang.add(gioHang);
            }
        } else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = Long.parseLong(String.valueOf(sanPhamMoi.getPrice()));
            GioHang gioHang = new GioHang();
            gioHang.setPrice((int) gia);
            gioHang.setSoluong(soluong);
            gioHang.setId(sanPhamMoi.getId());
            gioHang.setTensp(sanPhamMoi.getTensp());
            gioHang.setMota(sanPhamMoi.getMota());
            gioHang.setHinhanh(sanPhamMoi.getHinhanh());
            Utils.manggiohang.add(gioHang);
        }
        int totalItem = 0;
        for (int i=0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    //Hiển thị chi tiết sp
    private void initData() {
        sanPhamMoi = sanPhamMoi = (SanPhamMoi) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sanPhamMoi.getTensp());
        mota.setText(sanPhamMoi.getMota());
        int drawableResourceId = getApplicationContext().getResources().getIdentifier(sanPhamMoi.getHinhanh(), "drawable", getApplicationContext().getPackageName());
        Glide.with(getApplicationContext()).load(drawableResourceId).into(imghinhanh); //Chuyển đổi để xuất hình ảnh
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giasp.setText("Giá: "+decimalFormat.format(sanPhamMoi.getPrice())+" Đ");//Set chấm phẩy cho giá sp
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};//Set số lượng sp
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(adapterspin);
    }

    private void initView() {
        tensp = findViewById(R.id.txttensp);
        giasp = findViewById(R.id.txtgiasp);
        mota  = findViewById(R.id.txtmotachitiet);
        imghinhanh = findViewById(R.id.imgchitiet);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        spinner = findViewById(R.id.spinner);
        badge = findViewById(R.id.menu_sl);
        toolbarchitiet = findViewById(R.id.toolbarchitiet);
        FrameLayout frameLayoutgiohang = findViewById(R.id.framegiohang);
        frameLayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giohang = new Intent(getApplicationContext(),giohang.class);
                startActivity(giohang);
            }
        });

        if (Utils.manggiohang != null){
            int totalItem = 0;
            for (int i=0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (Utils.manggiohang != null) {
            int totalItem = 0;
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }


    public void BackTrangChu(View view){
        Intent intent =new Intent(this, trangchu.class);
        startActivity(intent);
    }


}