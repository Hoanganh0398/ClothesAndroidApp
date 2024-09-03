package com.example.detai;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.example.detai.Utils.Utils;
import com.example.detai.adapter.dm_adapter;
import com.example.detai.dm_sp.phanloai8_setdonu;
import com.example.detai.dm_sp.phanloai1_aosomi;
import com.example.detai.dm_sp.phanloai2_aothun;
import com.example.detai.dm_sp.phanloai3_aokieu;
import com.example.detai.dm_sp.phanloai4_quantay;
import com.example.detai.dm_sp.phanloai5_vay;
import com.example.detai.dm_sp.phanloai6_chanvay;
import com.example.detai.dm_sp.phanloai7_setdonam;
import com.example.detai.model.Loaisp;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class danhmuc extends AppCompatActivity {
    dm_adapter  loaispAdapter;
    List<Loaisp> mangloaisp;
    ListView listView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIWeb apiWeb;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc);
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        listView = findViewById(R.id.lvDM);
        mangloaisp = new ArrayList<>();

        if (Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }

        if (isConnected(this)){
            getLoaisp();
            getEventClick();
        }
        else {
            Toast.makeText(getApplicationContext(), "Không có Internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEventClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent sanpham1 = new Intent(getApplicationContext(), phanloai1_aosomi.class);
                        sanpham1.putExtra("loai",1001);
                        startActivity(sanpham1);
                        break;
                    case 1:
                        Intent sanpham2 = new Intent(getApplicationContext(), phanloai2_aothun.class);
                        sanpham2.putExtra("loai",1002);
                        startActivity(sanpham2);
                        break;
                    case 2:
                        Intent sanpham3 = new Intent(getApplicationContext(), phanloai3_aokieu.class);
                        sanpham3.putExtra("loai",1003);
                        startActivity(sanpham3);
                        break;
                    case 3:
                        Intent sanpham4 = new Intent(getApplicationContext(), phanloai4_quantay.class);
                        sanpham4.putExtra("loai",1004);
                        startActivity(sanpham4);
                        break;
                    case 4:
                        Intent sanpham5 = new Intent(getApplicationContext(), phanloai5_vay.class);
                        sanpham5.putExtra("loai",1005);
                        startActivity(sanpham5);
                        break;
                    case 5:
                        Intent sanpham6 = new Intent(getApplicationContext(),phanloai6_chanvay .class);
                        sanpham6.putExtra("loai",1006);
                        startActivity(sanpham6);
                        break;
                    case 6:
                        Intent sanpham7 = new Intent(getApplicationContext(), phanloai7_setdonam.class);
                        sanpham7.putExtra("loai",1007);
                        startActivity(sanpham7);
                        break;
                    case 7:
                        Intent sanpham8 = new Intent(getApplicationContext(), phanloai8_setdonu.class);
                        sanpham8.putExtra("loai",1008);
                        startActivity(sanpham8);
                        break;
                }
            }
        });
    }


    private void getLoaisp(){
        compositeDisposable.add(apiWeb.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                  loaispModel -> {
                      if(loaispModel.isSuccess()){
                          mangloaisp = loaispModel.getResult();
                          loaispAdapter = new dm_adapter(getApplicationContext(), mangloaisp);
                          listView.setAdapter(loaispAdapter);
                      }
                  }
                ))
                ;
    }
    public void sanphamyeuthich(View view){
        Intent intent = new Intent(this, sanphamyeuthich.class);
        startActivity(intent);
    }
    public void sanphamnoibat(View view){
        Intent intent =new Intent(this, sanphamnoibat.class);
        startActivity(intent);
    }
    private boolean isConnected (Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) ||(mobile != null && mobile.isConnected()) ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}