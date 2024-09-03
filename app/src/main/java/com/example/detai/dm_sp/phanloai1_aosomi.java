package com.example.detai.dm_sp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.detai.R;
import com.example.detai.Utils.Utils;
import com.example.detai.adapter.SpAosomi_Adapter;
import com.example.detai.model.SanPhamMoi;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class phanloai1_aosomi extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIWeb apiWeb;
    RecyclerView recyclerspdm;
    List<SanPhamMoi> mangSP;
    SpAosomi_Adapter spAosomi_adapter;
    Toolbar toolbar;
    int page = 1;
    int loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phanloai1_aosomi);
        toolbar = findViewById(R.id.toolbarAosomi);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerspdm = findViewById(R.id.recyclespdm);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerspdm.setLayoutManager(layoutManager);
        recyclerspdm.setHasFixedSize(true);
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        loai = getIntent().getIntExtra("loai",1001);
        mangSP = new ArrayList<>();
        if (isConnected(this)){
            getData();
        }
        else {
            Toast.makeText(getApplicationContext(), "Không có Internet", Toast.LENGTH_SHORT).show();
        }
    }
    private void getData(){
        compositeDisposable.add(apiWeb.getSPtheoDM(page,loai)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                mangSP = sanPhamMoiModel.getResult();
                                spAosomi_adapter = new SpAosomi_Adapter(getApplicationContext(), mangSP);
                                recyclerspdm.setAdapter(spAosomi_adapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được với server"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
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
