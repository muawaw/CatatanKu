package com.example.catatanku;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    @Expose
    @SerializedName("id_expense") private int id;
    @Expose
    @SerializedName("kategori") private String kategori;
    @Expose
    @SerializedName("jumlah") private String Jumlah;
    @Expose
    @SerializedName("tanggal") private String date;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String jumlah) {
        Jumlah = jumlah;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
