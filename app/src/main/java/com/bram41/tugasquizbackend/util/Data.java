package com.bram41.tugasquizbackend.util;

public class Data {
    private String id;
    private String soal;
    private String benar;
    private String pertama;
    private String kedua;
    private String ketiga;
    private String keempat;

    public Data() {
        id = "";
        soal = "";
        benar = "";
        pertama = "";
        kedua = "";
        ketiga = "";
        keempat = "";

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getBenar() {
        return benar;
    }

    public void setBenar(String benar) {
        this.benar = benar;
    }

    public String getPertama() {
        return pertama;
    }

    public void setPertama(String pertama) {
        this.pertama = pertama;
    }

    public String getKedua() {
        return kedua;
    }

    public void setKedua(String kedua) {
        this.kedua = kedua;
    }

    public String getKetiga() {
        return ketiga;
    }

    public void setKetiga(String ketiga) {
        this.ketiga = ketiga;
    }

    public String getKeempat() {
        return keempat;
    }

    public void setKeempat(String keempat) {
        this.keempat = keempat;
    }

    public Data(String id, String soal, String benar, String pertama, String kedua, String ketiga, String keempat) {
        this.id = id;
        this.soal = soal;
        this.benar = benar;
        this.pertama = pertama;
        this.kedua = kedua;
        this.ketiga = ketiga;
        this.keempat = keempat;
    }
}