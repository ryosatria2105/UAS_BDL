package service;

import model.Kategori;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriService {

    public List<Kategori> getAll() {
        List<Kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM KATEGORI ORDER BY ID_KATEGORI";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Kategori k = new Kategori();
                k.setIdKategori(rs.getString("ID_KATEGORI"));
                k.setNamaKategori(rs.getString("NAMA_KATEGORI"));
                list.add(k);
            }

        } catch (SQLException e) { e.printStackTrace(); }

        return list;
    }

    public boolean insert(Kategori k) {
        String sql = "INSERT INTO KATEGORI VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, k.getIdKategori());
            ps.setString(2, k.getNamaKategori());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Kategori k) {
        String sql = "UPDATE KATEGORI SET NAMA_KATEGORI=? WHERE ID_KATEGORI=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, k.getNamaKategori());
            ps.setString(2, k.getIdKategori());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM KATEGORI WHERE ID_KATEGORI=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }
    
    public boolean deleteAll() {
    String sql = "DELETE FROM KATEGORI";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public boolean existsById(String id) {
    String sql = "SELECT COUNT(*) FROM kategori WHERE id_kategori = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1) > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


}
