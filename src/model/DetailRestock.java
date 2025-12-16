package model;

public class DetailRestock {

    private String idDetailRestock;
    private String idRestock;
    private String idBarang;
    private String namaBarang;
    private int jumlah;
    private double hargaBeli;

    public DetailRestock() {
    }

    public DetailRestock(String idDetailRestock, String idRestock,
                         String idBarang, int jumlah, double hargaBeli)
    
    
    
    {
        this.idDetailRestock = idDetailRestock;
        this.idRestock = idRestock;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.hargaBeli = hargaBeli;
    }

    public String getIdDetailRestock() {
        return idDetailRestock;
    }

    public void setIdDetailRestock(String idDetailRestock) {
        this.idDetailRestock = idDetailRestock;
    }

    public String getIdRestock() {
        return idRestock;
    }

    public void setIdRestock(String idRestock) {
        this.idRestock = idRestock;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public double getSubtotal() {
        return jumlah * hargaBeli;
    }

    @Override
    public String toString() {
        return idDetailRestock;
    }
}
