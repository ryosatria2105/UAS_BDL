package model;

public class Barang {

    private String idBarang;      // VARCHAR(5)
    private String idKategori;    // VARCHAR(5)
    private String namaBarang;    // VARCHAR(50)
    private double harga;         // DECIMAL(12,2)
    private int stock;            // INT4

    // Tambahan baru
    private String namaKategori;

    public Barang() {
    }

    public Barang(String idBarang, String idKategori, String namaBarang, double harga, int stock) {
        this.idBarang = idBarang;
        this.idKategori = idKategori;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stock = stock;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter-setter tambahan untuk NAMA KATEGORI
    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
}
