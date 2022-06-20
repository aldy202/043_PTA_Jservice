package com.example.jservice3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Jservice/insert.php")
    public Call<User> insert(
            @Field("namapemilik") String namapemilik,
            @Field("jenisbarang") String jenisbarang,
            @Field("detail") String detail,
            @Field("metode") String metode,
            @Field("status") String status
    );

    @POST("Jservice/readuser.php")
    public  Call<List<User>> read();

    @FormUrlEncoded
    @POST("Jservice/update.php")
    public Call<User> update(
            @Field("id") String id,
            @Field("namapemilik") String namapemilik,
            @Field("jenisbarang") String jenisbarang,
            @Field("detail") String detail,
            @Field("metode") String metode,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("Jservice/delete.php")
    public Call<User> delete(
            @Field("id") String id
    );






}
