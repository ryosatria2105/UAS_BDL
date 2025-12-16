package model;

public class JenisPembayaran {
    private String idJenis;
    private String namaJenis;
    private String keterangan;

    public JenisPembayaran() {}

    public JenisPembayaran(String idJenis, String namaJenis, String keterangan) {
        this.idJenis = idJenis;
        this.namaJenis = namaJenis;
        this.keterangan = keterangan;
    }

    public String getIdJenis() { return idJenis; }
    public void setIdJenis(String idJenis) { this.idJenis = idJenis; }

    public String getNamaJenis() { return namaJenis; }
    public void setNamaJenis(String namaJenis) { this.namaJenis = namaJenis; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
  
@Override

public String toString() {
    return namaJenis;   // cuma nama doang yang muncul di combo
}




}


