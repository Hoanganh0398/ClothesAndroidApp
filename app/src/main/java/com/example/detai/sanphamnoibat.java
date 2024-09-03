package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sanphamnoibat extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spnoibat);
    }
    public void giohang(View view){
        Intent intent =new Intent(this, giohang.class);
        startActivity(intent);
    }
    public void chitietsanpham(View view){
        Intent intent =new Intent(this, chitietsanpham.class);
        startActivity(intent);
    }

}