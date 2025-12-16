package model;

public class Supplier {
    private String idSupplier;      // VARCHAR(5)
    private String namaSupplier;    // VARCHAR(50)
    private String kontak;          // VARCHAR(12)
    private String alamat; 
    public Supplier() {
}

    
public Supplier(String idSupplier, String namaSupplier, String kontak, String alamat) {
    this.idSupplier = idSupplier;
    this.namaSupplier = namaSupplier;
    this.kontak = kontak;
    this.alamat = alamat;
    
    
}
// VARCHAR(50)

    public String getIdSupplier() { return idSupplier; }
    public void setIdSupplier(String idSupplier) { this.idSupplier = idSupplier; }

    public String getNamaSupplier() { return namaSupplier; }
    public void setNamaSupplier(String namaSupplier) { this.namaSupplier = namaSupplier; }

    public String getKontak() { return kontak; }
    public void setKontak(String kontak) { this.kontak = kontak; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
}
