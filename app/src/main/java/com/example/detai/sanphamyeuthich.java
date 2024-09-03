package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sanphamyeuthich extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanphamyeuthich);
    }
    public void Back(View view){
        Intent intent =new Intent(this, danhmuc.class);
        startActivity(intent);
    }

}