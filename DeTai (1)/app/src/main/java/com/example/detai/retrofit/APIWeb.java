package com.example.detai.retrofit;

import com.example.detai.model.DonhangModel;
import com.example.detai.model.LoaispModel;
import com.example.detai.model.SanPhamMoiModel;
import com.example.detai.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIWeb {
    @GET("getloaisp.php")
    Observable<LoaispModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSPMoi();

//dùng get đổ dữ liệu từ database
    @GET("getspnoibat.php")
    Observable<SanPhamMoiModel> getSPNoibat();
    @GET("getdonhang.php")
    Observable<DonhangModel> getDonhang();
//dùng POST lấy dữ liệu sản phẩm để tìm xuất ra thông tin sp
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel>  getSPtheoDM(
            @Field("page") int page,
            @Field("loai") int loai
    );
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel>  dangKi(
            @Field("hoten") String hoten,
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("matkhau") String matkhau
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel>  dangNhap(
            @Field("email") String email,
            @Field("matkhau") String matkhau
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel>  createOrder(
            @Field("hoten") String hoten,
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("diachi") String diachi,
            @Field("tongtien") String tongtien,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel>  timkiem(
            @Field("search") String search
    );
    @POST("submit_new.php")
    @FormUrlEncoded
    Observable<UserModel>  resetPass(
            @Field("email") String email,
            @Field("password") String password
    );
}
