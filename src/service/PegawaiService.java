package service;

import model.Pegawai;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PegawaiService {

    // ===============================
    // GET ALL
    // ===============================
    public List<Pegawai> getAll() {
        List<Pegawai> list = new ArrayList<>();
        String sql = "SELECT * FROM PEGAWAI ORDER BY ID_PEGAWAI ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pegawai p = new Pegawai();
                p.setId(rs.getString("id_pegawai"));
                p.setNama(rs.getString("nama_pegawai"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setJabatan(rs.getString("jabatan"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===============================
    // LOGIN
    // ===============================
    public Pegawai login(String username, String password, String role) {
        String sql = "SELECT * FROM PEGAWAI WHERE USERNAME = ? AND PASSWORD = ? AND JABATAN = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pegawai p = new Pegawai();
                p.setId(rs.getString("id_pegawai"));
                p.setNama(rs.getString("nama_pegawai"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setJabatan(rs.getString("jabatan"));
                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===============================
    // GENERATE AUTO ID
    // ===============================
    private String generateID() {
    String sql = "SELECT MAX(CAST(SUBSTRING(id_pegawai, 3) AS INTEGER)) AS maxid FROM pegawai";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int last = rs.getInt("maxid");
            int next = last + 1;
            return String.format("PG%03d", next);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "PG001";
}


    // ===============================
    // INSERT (untuk admin)
    // ===============================
    public boolean insert(Pegawai p) {
        String newID = generateID();

        String sql = "INSERT INTO PEGAWAI VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newID);
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJabatan());
            ps.setString(4, p.getUsername());
            ps.setString(5, p.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ===============================
    // REGISTER (user daftar)
    // ===============================
    public boolean register(Pegawai p) {
        return insert(p); // gunakan insert(), supaya konsisten
    }

    // ===============================
    // UPDATE
    // ===============================
    public boolean update(Pegawai p) {
        String sql = "UPDATE PEGAWAI SET nama_pegawai=?, jabatan=?, username=?, password=? WHERE id_pegawai=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNama());
            ps.setString(2, p.getJabatan());
            ps.setString(3, p.getUsername());
            ps.setString(4, p.getPassword());
            ps.setString(5, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===============================
    // DELETE
    // ===============================
    public boolean delete(String id) {
        String sql = "DELETE FROM PEGAWAI WHERE ID_PEGAWAI=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===============================
    // DELETE ALL
    // ===============================
    public boolean deleteAll() {
        String sql = "DELETE FROM PEGAWAI";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===============================
    // CEK USERNAME
    // ===============================
    public boolean isUsernameExist(String username) {
        String sql = "SELECT 1 FROM PEGAWAI WHERE USERNAME=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   public boolean existsForForgot(String username) {
    String sql = "SELECT 1 FROM PEGAWAI WHERE USERNAME = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        return rs.next(); // true kalau username ditemukan

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
   
   public boolean existsByUsername(String username) {
    String sql = "SELECT COUNT(*) FROM pegawai WHERE username = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1) > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


}
