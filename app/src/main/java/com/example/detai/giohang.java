package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.detai.EventBus.TinhTongEvent;
import com.example.detai.Utils.Utils;
import com.example.detai.adapter.GioHang_Adapter;
import com.example.detai.model.GioHang;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class giohang extends AppCompatActivity {
    TextView giohagtrong, tongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnthanhtoan;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    long tongtiensp;
    GioHang_Adapter gioHangAdapter;
    List<GioHang> gioHangList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
        if(Utils.mangmuahang != null){
            Utils.mangmuahang.clear();
        }
        initView();
        initControl();
        tinhTongtien();
    }
//Tính tổng tiền trong giỏ hàng
    private void tinhTongtien() {
        tongtiensp = 0;
        for (int i = 0; i<Utils.mangmuahang.size(); i++){
            tongtiensp = tongtiensp+ (Utils.mangmuahang.get(i).getPrice()* Utils.mangmuahang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(tongtiensp));
    }

    public void thanhtoan(View view){
        if(Utils.manggiohang.size()==0){
            Toast.makeText(getApplicationContext(), "Chưa có sản phẩm nào đuợc thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, thanhtoan.class);
            intent.putExtra("tongtien", tongtiensp);
           // Utils.manggiohang.clear();
            startActivity(intent);
        }
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(Utils.manggiohang.size()==0){
            Toast.makeText(getApplicationContext(), "Giỏ hàng có (0) sản phẩm!", Toast.LENGTH_SHORT).show();
            giohagtrong.setVisibility(View.VISIBLE);
        } else {
            giohagtrong.setVisibility(View.INVISIBLE);
            gioHangAdapter = new GioHang_Adapter(getApplicationContext(), Utils.manggiohang);
            recyclerView.setAdapter(gioHangAdapter);
        }
    }

    private void initView() {
        giohagtrong = findViewById(R.id.txtgiohangtrong);
        tongtien = findViewById(R.id.txttongtien);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclegiohang);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event){
        if (event != null){
            tinhTongtien();
        }
    }

}