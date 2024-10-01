package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class thanhtoanbangthe extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanhtoanbangthe);
    }
    public void thanhtoan(View view){
        Intent intent =new Intent(this, thanhtoan.class);
        startActivity(intent);
    }
}