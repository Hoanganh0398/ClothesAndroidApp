package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.detai.Utils.Utils;
import com.example.detai.adapter.GioHang_Adapter;
import com.example.detai.adapter.Thanhtoan_Adapter;
import com.example.detai.model.GioHang;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Objects;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class thanhtoan extends AppCompatActivity {
    Toolbar toolbar;
    TextView txthoten, txtemail, txtsdt, txttongtien, txttongtien2;
    EditText editdiachi;
    Thanhtoan_Adapter thanhtoan_adapter;
    int totalItem;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIWeb apiWeb;
    RecyclerView recyclerView;
    Button btnthanhtoan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoan);
        initView();
        countItem();
        initControl();
    }

    private void countItem() {
        totalItem = 0;
        for (int i=0; i<Utils.mangmuahang.size(); i++){
            totalItem = totalItem+ Utils.mangmuahang.get(i).getSoluong();
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
        //Truyền dữ liệu tổng tiền từ giỏ hàng qua
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        long tongtien = getIntent().getLongExtra("tongtien",0);
        txttongtien.setText("Tổng thanh toán: "+decimalFormat.format(tongtien)+"đ");
        txttongtien2.setText(decimalFormat.format(tongtien)+"đ");
        txthoten.setText("Khách hàng: "+Utils.user_current.getHoten());
        txtemail.setText("Email: "+Utils.user_current.getEmail());
        txtsdt.setText("Liên hệ: "+Utils.user_current.getSdt());

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        thanhtoan_adapter = new Thanhtoan_Adapter(getApplicationContext(), Utils.mangmuahang);
        recyclerView.setAdapter(thanhtoan_adapter);
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_diachi = editdiachi.getText().toString().trim();
                if(TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    String str_hoten = Utils.user_current.getHoten();
                    String str_email = Utils.user_current.getEmail();
                    String str_sdt = Utils.user_current.getSdt();
                    Log.d("test", new Gson().toJson(Utils.mangmuahang));
                    compositeDisposable.add(apiWeb.createOrder(str_hoten,str_email,str_sdt,str_diachi, String.valueOf(tongtien),totalItem,new Gson().toJson(Utils.mangmuahang))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        //clear mảng giỏ hàng bằng cách chạy qua mangmuahang và xóa item trùng
                                            for(int i=0; i<Utils.mangmuahang.size(); i++){
                                                GioHang gioHang = Utils.mangmuahang.get(i);
                                                if (Utils.manggiohang.contains(gioHang)){
                                                    Utils.manggiohang.remove(gioHang);
                                                }
                                            }
                                            Utils.mangmuahang.clear();
                                            //Lưu giỏ hàng
                                            Paper.book().write("giohang",Utils.manggiohang);
                                            Intent intent =new Intent(getApplicationContext(), hoadon.class);
                                            startActivity(intent);
                                            Toast.makeText(getApplicationContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                    },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(),throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }

    private void initView() {
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        toolbar = findViewById(R.id.toolbarthanhtoan);
        txthoten = findViewById(R.id.txthoten);
        txtemail = findViewById(R.id.txtemail);
        txtsdt = findViewById(R.id.txtsodienthoai);
        txttongtien = findViewById(R.id.txttongtien);
        txttongtien2 = findViewById(R.id.txttongtien2);
        editdiachi = findViewById(R.id.editdiachi);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
        recyclerView = findViewById(R.id.recyclethanhtoan);
    }

    public void Backtrangchu(View view){
        Intent intent =new Intent(this, trangchu.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}