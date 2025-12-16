package model;

public class Kategori {
    private String idKategori;     // VARCHAR(5)
    private String namaKategori;   // VARCHAR(50)

    public String getIdKategori() { return idKategori; }
    public void setIdKategori(String idKategori) { this.idKategori = idKategori; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }
}
