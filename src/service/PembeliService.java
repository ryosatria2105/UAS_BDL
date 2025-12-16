package service;

import model.Pembeli;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PembeliService {

    public List<Pembeli> getAll() {
        List<Pembeli> list = new ArrayList<>();
        String sql = "SELECT * FROM PEMBELI ORDER BY ID_PEMBELI";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pembeli p = new Pembeli();
                p.setIdPembeli(rs.getString("ID_PEMBELI"));
                p.setNamaPembeli(rs.getString("NAMA_PEMBELI"));
                p.setNoTelepon(rs.getString("NO_TELEPON"));
                p.setAlamat(rs.getString("ALAMAT"));
                list.add(p);
            }

        } catch (SQLException e) { e.printStackTrace(); }

        return list;
    }

    public boolean insert(Pembeli p) {
        String sql = "INSERT INTO PEMBELI VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getIdPembeli());
            ps.setString(2, p.getNamaPembeli());
            ps.setString(3, p.getNoTelepon());
            ps.setString(4, p.getAlamat());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean update(Pembeli p) {
        String sql = "UPDATE PEMBELI SET NAMA_PEMBELI=?, NO_TELEPON=?, ALAMAT=? WHERE ID_PEMBELI=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNamaPembeli());
            ps.setString(2, p.getNoTelepon());
            ps.setString(3, p.getAlamat());
            ps.setString(4, p.getIdPembeli());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM PEMBELI WHERE ID_PEMBELI=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); }

        return false;
    }
    public boolean deleteAll() {
    String sql = "DELETE FROM PEMBELI";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
        // ==========================================================
    //              FIX: AUTO ID + CEK DATA EXISTING
    // ==========================================================

    // Generate ID Pembeli otomatis: P001, P002, dst
    public String generateIdPembeli() {
        String sql = "SELECT id_pembeli FROM pembeli ORDER BY id_pembeli DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String last = rs.getString(1).substring(1); // ambil angka saja
                int next = Integer.parseInt(last) + 1;
                return "P" + String.format("%03d", next);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "P001"; // kalau tabel kosong
    }


    // Cek apakah pembeli sudah ada berdasarkan nama atau telepon
    public Pembeli getByNamaAtauTelepon(String nama, String telepon) {
        String sql = "SELECT * FROM pembeli WHERE nama_pembeli = ? OR no_telepon = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);
            ps.setString(2, telepon);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pembeli p = new Pembeli();
                p.setIdPembeli(rs.getString("id_pembeli"));
                p.setNamaPembeli(rs.getString("nama_pembeli"));
                p.setNoTelepon(rs.getString("no_telepon"));
                p.setAlamat(rs.getString("alamat"));
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean existsById(String idPembeli) {
    String sql = "SELECT COUNT(*) FROM pembeli WHERE id_pembeli = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, idPembeli);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // TRUE kalau ID sudah ada
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
    
    public Pembeli getById(String id) {
    String sql = "SELECT * FROM pembeli WHERE id_pembeli = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Pembeli p = new Pembeli();
            p.setIdPembeli(rs.getString("id_pembeli"));
            p.setNamaPembeli(rs.getString("nama_pembeli"));
            p.setNoTelepon(rs.getString("no_telepon"));
            p.setAlamat(rs.getString("alamat"));
            return p;
        }

    } catch (Exception e) { e.printStackTrace(); }

    return null;
}

    
    


    
    

}
