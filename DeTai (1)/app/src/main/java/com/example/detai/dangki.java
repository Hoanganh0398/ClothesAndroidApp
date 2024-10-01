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

public class dangki extends AppCompatActivity {

    EditText hoten,email,matkhau,repass,sdt;
    Button btndangki;
    APIWeb apiWeb;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki);
        initView();

    }

    private void initView() {
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        hoten = findViewById(R.id.hoten);
        email = findViewById(R.id.email);
        matkhau = findViewById(R.id.password);
        repass = findViewById(R.id.repass);
        sdt = findViewById(R.id.sdt);
        btndangki = findViewById(R.id.btndangki);
    }

    public void quaylaidangnhap(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void dangkithanhcong(View view) {
        dangKi();
    }
    public void dangKi(){
        String str_hoten = hoten.getText().toString().trim();
        String str_email = email.getText().toString().trim();
        String str_sdt = sdt.getText().toString().trim();
        String str_pass = matkhau.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        //Kiểm tra xem người dùng có nhập sót thông tin không
        //Toast lòa dòng tb ngắn
        if (TextUtils.isEmpty(str_hoten)){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_sdt)){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập Mật khẩu!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_repass)){
            Toast.makeText(getApplicationContext(), "Vui lòng Xác nhận mật khẩu!", Toast.LENGTH_SHORT).show();
        } else {
            if (str_pass.equals(str_repass)){
                // Gửi dữ liệu đăng ký lên máy chủ sử dụng Retrofit
                compositeDisposable.add(apiWeb.dangKi(str_hoten,str_email,str_sdt,str_pass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if (userModel.isSuccess()){
                                        //Lấy dữ liệu người dùng hiện tại
                                        Utils.user_current.setEmail(str_email);
                                        Utils.user_current.setMatkhau(str_pass);
                                        Utils.user_current.setSdt(str_sdt);
                                        Utils.user_current.setHoten(str_hoten);
                                        //Thông báo đk thành công, chuyển sang trang đk
                                        Toast.makeText(getApplicationContext(),"Đăng ký thành công", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),dangkithanhcong.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(),throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            } else {
                Toast.makeText(this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}