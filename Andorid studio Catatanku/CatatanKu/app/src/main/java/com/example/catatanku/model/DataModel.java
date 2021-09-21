package com.example.catatanku.model;

public class DataModel {

    public Integer id;
    public String kategori;
    public String jumlah;
    public String tanggal;

    public DataModel() {

    }

    public DataModel(Integer id, String kategori, String jumlah, String tanggal) {
        this.id = id;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public Integer getID() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
