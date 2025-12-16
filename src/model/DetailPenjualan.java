package model;

public class DetailPenjualan {
    private String idDetailPenjualan;  // DP001
    private String idPenjualan;        // FK ke PENJUALAN
    private String idBarang;           // FK ke BARANG
    private int jumlah;
    private double subtotal;

    public String getIdDetailPenjualan() { return idDetailPenjualan; }
    public void setIdDetailPenjualan(String idDetailPenjualan) { this.idDetailPenjualan = idDetailPenjualan; }

    public String getIdPenjualan() { return idPenjualan; }
    public void setIdPenjualan(String idPenjualan) { this.idPenjualan = idPenjualan; }

    public String getIdBarang() { return idBarang; }
    public void setIdBarang(String idBarang) { this.idBarang = idBarang; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
