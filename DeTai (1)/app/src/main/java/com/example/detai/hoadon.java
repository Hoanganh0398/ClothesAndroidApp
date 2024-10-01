package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.detai.Utils.Utils;
import com.example.detai.adapter.SanPhamMoi_Adapter;
import com.example.detai.adapter.Thanhtoan_Adapter;
import com.example.detai.model.Donhang;
import com.example.detai.model.GioHang;
import com.example.detai.model.SanPhamMoi;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class hoadon extends AppCompatActivity {
    TextView idkh;
    TextView tenkh;
    TextView diachikh;
    TextView iddh;
    TextView date;
    TextView tong;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Donhang donhang;
    APIWeb apiWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoadon);
        initView();
        getDonHang();
    }

    private void initView() {
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        tenkh = findViewById(R.id.txtten);
        idkh = findViewById(R.id.txtid);
        iddh = findViewById(R.id.txtidhoadon);
        diachikh = findViewById(R.id.txtdiachi);
        date = findViewById(R.id.txtdate);
        tong = findViewById(R.id.txttong);
    }

    private void initData() {
        tenkh.setText(donhang.getHoten());
        idkh.setText(String.valueOf(Utils.user_current.getId()));
        iddh.setText(String.valueOf(donhang.getId()));
        diachikh.setText(donhang.getDiachi());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(donhang.getThoidiemdathang());
        date.setText(dateString);
        long tonghd = Long.parseLong(donhang.getTongtien());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tong.setText(decimalFormat.format(tonghd) + " Đ");
    }

    private void getDonHang() {
        compositeDisposable.add(apiWeb.getDonhang()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donhangModel -> {
                            if (donhangModel.isSuccess()) {
                                List<Donhang> donhangList = donhangModel.getResult();
                                if (!donhangList.isEmpty()) {
                                    donhang = donhangList.get(0);
                                    initData();
                                }
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được với server" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    public void backtrangchuhoadon(View view) {
        Intent intent = new Intent(this, trangchu.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}