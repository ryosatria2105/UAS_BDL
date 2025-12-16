package service;

import model.Barang;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangService {

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
String sql = 
    "SELECT b.ID_BARANG, b.NAMA_BARANG, b.HARGA, b.STOCK, k.NAMA_KATEGORI " +
    "FROM BARANG b " +
    "JOIN KATEGORI k ON b.ID_KATEGORI = k.ID_KATEGORI " +
    "ORDER BY b.ID_BARANG ASC";



        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

          while (rs.next()) {
    Barang b = new Barang();
    b.setIdBarang(rs.getString("ID_BARANG"));
    b.setNamaBarang(rs.getString("NAMA_BARANG"));
    b.setHarga(rs.getDouble("HARGA"));
    b.setStock(rs.getInt("STOCK"));
    b.setNamaKategori(rs.getString("NAMA_KATEGORI")); // << PENTING
    list.add(b);
}


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(Barang b) {
        String sql = "INSERT INTO BARANG VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getIdBarang());
            ps.setString(2, b.getIdKategori());
            ps.setString(3, b.getNamaBarang());
            ps.setDouble(4, b.getHarga());
            ps.setInt(5, b.getStock());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Barang b) {
        String sql = "UPDATE BARANG SET ID_KATEGORI=?, NAMA_BARANG=?, HARGA=?, STOCK=? WHERE ID_BARANG=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getIdKategori());
            ps.setString(2, b.getNamaBarang());
            ps.setDouble(3, b.getHarga());
            ps.setInt(4, b.getStock());
            ps.setString(5, b.getIdBarang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(String id) {
        
        String sql = "DELETE FROM BARANG WHERE ID_BARANG=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }
    
    public boolean deleteAll() {
    String sql = "DELETE FROM BARANG";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public Barang getById(String idBarang) {
    String sql = "SELECT * FROM BARANG WHERE ID_BARANG = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, idBarang);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Barang b = new Barang();
            b.setIdBarang(rs.getString("ID_BARANG"));
            b.setIdKategori(rs.getString("ID_KATEGORI"));
            b.setNamaBarang(rs.getString("NAMA_BARANG"));
            b.setHarga(rs.getDouble("HARGA"));
            b.setStock(rs.getInt("STOCK"));
            return b;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public boolean updateStok(String idBarang, int stokBaru) {
    String sql = "UPDATE BARANG SET STOCK = ? WHERE ID_BARANG = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, stokBaru);
        ps.setString(2, idBarang);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

    public double getHargaById(String idBarang) {
    String sql = "SELECT HARGA FROM BARANG WHERE ID_BARANG = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, idBarang);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) return rs.getDouble("HARGA");

    } catch (SQLException e) { 
        e.printStackTrace(); 
    }
    return 0;
}
    
    // di dalam BarangService
public void kurangiStok(String idBarang, int jumlah) {
    Barang b = getById(idBarang);
    if (b == null) return;

    int stokBaru = b.getStock() - jumlah;
    if (stokBaru < 0) stokBaru = 0; // jaga-jaga

    updateStok(idBarang, stokBaru);
}

public boolean existsById(String idBarang) {
    String sql = "SELECT COUNT(*) FROM barang WHERE id_barang = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, idBarang);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}



    
    

}
