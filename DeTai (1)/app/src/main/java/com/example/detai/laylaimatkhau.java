package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.detai.Utils.Utils;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class laylaimatkhau extends AppCompatActivity {
    Button btnReset;
    EditText edtemail,edtpass,edtrepass;
    APIWeb apiWeb;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laylaimatkhau);
        initView();
        initControl();
    }
    public void dangnhap(View view){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        initView();
        initControl();
    }

    private void initControl() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email = edtemail.getText().toString().trim();
                String str_pass = edtpass.getText().toString().trim();
                String str_repass = edtrepass.getText().toString().trim();
                if (TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mail", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(str_pass)){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập Mật khẩu!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(str_repass)){
                    Toast.makeText(getApplicationContext(), "Vui lòng Xác nhận mật khẩu!", Toast.LENGTH_SHORT).show();}
                else {
                    if (str_pass.equals(str_repass)) {
                        compositeDisposable.add(apiWeb.resetPass(str_email, str_pass)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        userModel -> {
                                            if (userModel.isSuccess()) {
                                                Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        },
                                        throwable -> {
                                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                ));
                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        edtemail = findViewById(R.id.edtemail);
        edtpass = findViewById(R.id.edtpass);
        edtrepass = findViewById(R.id.edtrepass);
        btnReset = findViewById(R.id.btnreset);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}