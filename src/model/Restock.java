
package model;

import java.sql.Date;

public class Restock {

    private String idRestock;
    private String idPegawai;
    private String namaPegawai;
    private String idSupplier;
    private String namaSupplier;
    private Date tanggal;
    private int jumlah; // kolom JUMLAH di tabel RESTOCK

    public Restock() {
    }

    public Restock(String idRestock, String idPegawai, String idSupplier, Date tanggal, int jumlah) {
        this.idRestock = idRestock;
        this.idPegawai = idPegawai;
        this.idSupplier = idSupplier;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
    }

    public String getIdRestock() {
        return idRestock;
    }

    public void setIdRestock(String idRestock) {
        this.idRestock = idRestock;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return idRestock;
    }
}
