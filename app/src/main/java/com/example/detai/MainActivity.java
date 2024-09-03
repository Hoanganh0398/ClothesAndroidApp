package com.example.detai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.detai.Utils.Utils;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    EditText email,matkhau;
    Button btndangki;
    APIWeb apiWeb;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        Paper.init(this);
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        email = findViewById(R.id.email);
        matkhau = findViewById(R.id.password);
        //Đọc dữ liệu trong máy
        if(Paper.book().read("email") != null && Paper.book().read("matkhau") != null){
            email.setText(Paper.book().read("email"));
            matkhau.setText(Paper.book().read("matkhau"));
        }
    }
    public void dangki(View view){
        Intent intent =new Intent(this, dangki.class);
        startActivity(intent);
    }
    public void laylaimatkhau(View view){
        Intent intent =new Intent(this, laylaimatkhau.class);
        startActivity(intent);
    }

    public void Login(View view){
        String str_email = email.getText().toString().trim();
        String str_pass = matkhau.getText().toString().trim();
        if (TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập Mật khẩu!", Toast.LENGTH_SHORT).show();
        } else {
            //Lưu
            Paper.book().write("email",str_email);
            Paper.book().write("matkhau",str_pass);
            // Gửi dữ liệu đăng nhập lên máy chủ sử dụng Retrofit
            compositeDisposable.add(apiWeb.dangNhap(str_email, str_pass)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userModel -> {
                                if (userModel.isSuccess()) {
                                    isLogin = true;
                                    Paper.book().write("isLogin",isLogin);
                                    //Lấy dữ liệu người dùng hiện tại
                                    Utils.user_current = userModel.getResult().get(0);
                                    //Thông báo đk thành công, chuyển sang trang đk
                                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(this, trangchu.class);
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
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.user_current.getEmail() != null && Utils.user_current.getMatkhau() != null){
            email.setText(Utils.user_current.getEmail());
            matkhau.setText(Utils.user_current.getMatkhau());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}