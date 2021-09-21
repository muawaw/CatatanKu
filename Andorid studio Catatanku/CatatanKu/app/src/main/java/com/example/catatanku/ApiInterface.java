package com.example.catatanku;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("List.php")
    Call<Note> saveNote(
            @Field("kategori") String kategori,
            @Field("jumlah") String Jumlah,
            @Field("tanggal") String date
    );
}
