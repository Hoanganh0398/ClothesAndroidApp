package com.example.detai;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.detai.Utils.Utils;
import com.example.detai.adapter.SanPhamMoi_Adapter;
import com.example.detai.adapter.SanPhamNoiBat_Adapter;
import com.example.detai.model.SanPhamMoi;
import com.example.detai.retrofit.APIWeb;
import com.example.detai.retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class trangchu extends AppCompatActivity {

    RecyclerView recyclerView; //recycle sp mới
    RecyclerView recyclerViewNoibat; //recycle sp nổi bật
    NavigationView navigationView;
    ListView listView;
    Button btnSearch;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIWeb apiWeb;//API kết nối vs mysql
    List<SanPhamMoi> mangSPMoi;
    SanPhamMoi_Adapter sanPhamMoiAdapter;
    SanPhamNoiBat_Adapter sanPhamNoiBatAdapter;
    ImageSlider imageSlider;
    FrameLayout frameLayout; //Button chuyển sang giỏ hàng

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu);
        Anhxa();
    }
    private void Anhxa(){
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.recycle1);
        recyclerViewNoibat = findViewById(R.id.recycleNB);
        imageSlider = findViewById(R.id.image_slider);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2); //Số lượng sp trên 1 hàng
        RecyclerView.LayoutManager layoutManagerNB = new GridLayoutManager(this,16); //Số lượng sp trên 1 hàng
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewNoibat.setLayoutManager(layoutManagerNB);
        recyclerView.setHasFixedSize(true);
        recyclerViewNoibat.setHasFixedSize(true);
        frameLayout = findViewById(R.id.framegiohang);
        apiWeb = RetrofitClient.getInstance(Utils.BASE_URL).create(APIWeb.class);
        mangSPMoi = new ArrayList<>();
        if (Paper.book().read("giohang") != null){
            Utils.manggiohang = Paper.book().read("giohang");
        }
        if (Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }
        //Kiểm tra kết nối Internet
        if (isConnected(this)){
           getSPMoi();
           getSPNoibat();
        }
        else {
            Toast.makeText(getApplicationContext(), "Không có Internet", Toast.LENGTH_SHORT).show();
        }
        //Chuyển sang giỏ hàng
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giohang = new Intent(getApplicationContext(),giohang.class);
                startActivity(giohang);
            }
        });
        //Chuyển sang tìm kiếm
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timkiem = new Intent(getApplicationContext(),Timkiem.class);
                startActivity(timkiem);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void ActionViewFlipper(){
        List<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel("https://lemoney.com.vn/wp-content/uploads/2021/05/chuong_trinh_khuyen_mai-800x560.png", null));
        imagelist.add(new SlideModel("https://inlayngay.vn/media/data/blog/2021/to-roi-30-04/2mega-sale-background-with-discount_23-2148891128.jpg", null));
        imagelist.add(new SlideModel("https://png.pngtree.com/png-clipart/20210829/original/pngtree-autumn-promotion-fashion-banner-template-png-image_6669483.jpg", null));
        imagelist.add(new SlideModel("https://ekoizi.com/wp-content/uploads/2020/11/20-mau-content-thoi-trang-sieu-target.jpg", null));

        imageSlider.setImageList(imagelist, ScaleTypes.CENTER_CROP);
    }
    //Lấy các sản phẩm mới
    private void getSPMoi() {
        compositeDisposable.add(apiWeb.getSPMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                mangSPMoi = sanPhamMoiModel.getResult();
                                sanPhamMoiAdapter = new SanPhamMoi_Adapter(getApplicationContext(), mangSPMoi);
                                recyclerView.setAdapter(sanPhamMoiAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được với server"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );

    }
    //Lấy các sản phẩm nổi bật
    private void getSPNoibat() {
        compositeDisposable.add(apiWeb.getSPNoibat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                mangSPMoi = sanPhamMoiModel.getResult();
                                sanPhamNoiBatAdapter= new SanPhamNoiBat_Adapter(getApplicationContext(), mangSPMoi);
                                recyclerViewNoibat.setAdapter(sanPhamNoiBatAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được với server"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    public void danhmuc(View view){
        Intent intent =new Intent(this, danhmuc.class);
        startActivity(intent);
    }
    public void user(View view){
        Intent intent =new Intent(this, user1.class);
        startActivity(intent);
    }
    public void sanphamnoibat(View view){
        Intent intent = new Intent(this, sanphamnoibat.class);
        startActivity(intent);
    }
    public void chatbot(View view){
        Intent intent =new Intent(this, AI.class);
        startActivity(intent);
    }
//Kiểm tra kết nối Internet
    private boolean isConnected (Context context){
        ActionViewFlipper();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) ||(mobile != null && mobile.isConnected()) ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
