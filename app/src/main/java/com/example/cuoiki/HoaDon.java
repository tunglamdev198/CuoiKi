package com.example.cuoiki;

public class HoaDon {
    private String soXe;
    private double quangDuong;
    private String donGia;
    private int phanTram;

    public HoaDon(String soXe, double quangDuong, String donGia, int phanTram) {
        this.soXe = soXe;
        this.quangDuong = quangDuong;
        this.donGia = donGia;
        this.phanTram = phanTram;
    }

    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public double getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        this.quangDuong = quangDuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(int phanTram) {
        this.phanTram = phanTram;
    }
    public double tongTien(){
        return (Integer.parseInt(donGia)) * quangDuong * 0.5;
    }
}
