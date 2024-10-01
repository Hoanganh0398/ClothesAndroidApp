package com.example.detai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.detai.Utils.Utils;

import java.util.Objects;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class user2 extends AppCompatActivity {
    Toolbar toolbar;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView txthoten, txtemail, txtsdt, txtten2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user2);
        toolbar = findViewById(R.id.toolbaruser2);
        txthoten = findViewById(R.id.txthoten1);
        txtten2 = findViewById(R.id.txtten2);
        txtemail = findViewById(R.id.txtemail1);
        txtsdt = findViewById(R.id.txtsodienthoai1);
        initControl();
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
        txthoten.setText("Khách hàng: "+ Utils.user_current.getHoten());
        txtten2.setText(Utils.user_current.getHoten());
        txtemail.setText("Email: "+Utils.user_current.getEmail());
        txtsdt.setText("Liên hệ: "+Utils.user_current.getSdt());
    }

    public void user(View view){
        Intent intent =new Intent(this, user1.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}