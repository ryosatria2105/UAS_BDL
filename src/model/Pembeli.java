package model;

public class Pembeli {
    private String idPembeli;      // VARCHAR(5)
    private String namaPembeli;    // VARCHAR(50)
    private String noTelepon;      // VARCHAR(15)
    private String alamat;         // VARCHAR(50)

    public String getIdPembeli() { return idPembeli; }
    public void setIdPembeli(String idPembeli) { this.idPembeli = idPembeli; }

    public String getNamaPembeli() { return namaPembeli; }
    public void setNamaPembeli(String namaPembeli) { this.namaPembeli = namaPembeli; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
}
