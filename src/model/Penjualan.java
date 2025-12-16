package model;

import java.time.LocalDate;

public class Penjualan {
    private String idPenjualan;      // PJ001
    private String idPegawai;        // FK ke PEGAWAI
    private String idPembeli;        // boleh null
    private LocalDate tanggal;
    private double total;
    private String statusPembayaran; // "LUNAS" / "BELUM"

    public String getIdPenjualan() { return idPenjualan; }
    public void setIdPenjualan(String idPenjualan) { this.idPenjualan = idPenjualan; }

    public String getIdPegawai() { return idPegawai; }
    public void setIdPegawai(String idPegawai) { this.idPegawai = idPegawai; }

    public String getIdPembeli() { return idPembeli; }
    public void setIdPembeli(String idPembeli) { this.idPembeli = idPembeli; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getStatusPembayaran() { return statusPembayaran; }
    public void setStatusPembayaran(String statusPembayaran) { this.statusPembayaran = statusPembayaran; }
}
