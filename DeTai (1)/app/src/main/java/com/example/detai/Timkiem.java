package com.example.detai;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.detai.Utils.Utils;
import com.example.detai.adapter.SpAosomi_Adapter;
import com.example.detai.model.SanPhamMoi;
import com.example.detai.model.SanPhamMoiModel;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Timkiem extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtSearch;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIWeb apiWeb;
    List<SanPhamMoi> mangSP;
    SpAosomi_Adapter spAosomi_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timkiem);
        initView();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mangSP = new ArrayList<>();
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        toolbar = findViewById(R.id.toolbarSearch);
        edtSearch = findViewById(R.id.edtSearch);
        recyclerView = findViewById(R.id.recyclesearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //Thêm sự kiện cho thanh tìm kiếm
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(charSequence.length() == 0){
                    mangSP.clear();
                    spAosomi_adapter = new SpAosomi_Adapter(getApplicationContext(),mangSP);
                    recyclerView.setAdapter(spAosomi_adapter);
                } else {
                    getDataSearch(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void getDataSearch(String s) {
        mangSP.clear();
        compositeDisposable.add(apiWeb.timkiem(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                mangSP = sanPhamMoiModel.getResult();
                                spAosomi_adapter = new SpAosomi_Adapter(getApplicationContext(),mangSP);
                                recyclerView.setAdapter(spAosomi_adapter);
                            } else {
                                //Toast.makeText(getApplicationContext(),sanPhamMoiModel.getMessage(), Toast.LENGTH_SHORT).show();
                                mangSP.clear();
                                spAosomi_adapter.notifyDataSetChanged();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
