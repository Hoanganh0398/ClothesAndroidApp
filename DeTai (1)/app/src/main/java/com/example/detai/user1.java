package com.example.detai;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.detai.Utils.Utils;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;

public class user1 extends AppCompatActivity {
 // Bật tắt sáng tối
    Switch switcher;

    TextView txtten2,txtemail;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        txtten2 = findViewById(R.id.txtnameuser1);
        txtemail = findViewById(R.id.txtemailuser1);


        txtten2.setText(Utils.user_current.getHoten());
        txtemail.setText("Email: "+Utils.user_current.getEmail());

        //bật tắt sáng tối
        switcher =findViewById(R.id.switcher);

        sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if (nightMode) {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });
    }
    // khi bật tắt sáng tối, tạo lại trang
    @Override
    public void recreate(){
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public void user1trolaidangnhap(View view){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void user1(View view){
        Intent intent =new Intent(this, user2.class);
        startActivity(intent);
    }
    public void UserBacktrangchu(View view){
        Intent intent = new Intent(this, trangchu.class);
        startActivity(intent);
    }

}

