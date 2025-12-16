package service;

import model.Supplier;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierService {

    public List<Supplier> getAll() {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM SUPPLIER ORDER BY ID_SUPPLIER";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Supplier s = new Supplier();
                s.setIdSupplier(rs.getString("ID_SUPPLIER"));
                s.setNamaSupplier(rs.getString("NAMA_SUPPLIER"));
                s.setKontak(rs.getString("KONTAK"));
                s.setAlamat(rs.getString("ALAMAT"));
                list.add(s);
            }

        } catch (SQLException e) { e.printStackTrace(); }

        return list;
    }

    public boolean insert(Supplier s) {
        String sql = "INSERT INTO SUPPLIER VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getIdSupplier());
            ps.setString(2, s.getNamaSupplier());
            ps.setString(3, s.getKontak());
            ps.setString(4, s.getAlamat());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Supplier s) {
        String sql = "UPDATE SUPPLIER SET NAMA_SUPPLIER=?, KONTAK=?, ALAMAT=? WHERE ID_SUPPLIER=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNamaSupplier());
            ps.setString(2, s.getKontak());
            ps.setString(3, s.getAlamat());
            ps.setString(4, s.getIdSupplier());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM SUPPLIER WHERE ID_SUPPLIER=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }
    
    public boolean deleteAll() {
    String sql = "DELETE FROM SUPPLIER";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    public boolean existsById(String idSupplier) {
    String sql = "SELECT COUNT(*) FROM supplier WHERE id_supplier = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, idSupplier);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // true kalau PK sama
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}


}
