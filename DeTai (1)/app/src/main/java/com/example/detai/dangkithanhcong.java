package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class dangkithanhcong extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangkithanhcong);
    }
    public void dangkithanhcong1(View view){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}