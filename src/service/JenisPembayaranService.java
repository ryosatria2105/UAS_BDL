package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.JenisPembayaran;
import util.DBConnection;

public class JenisPembayaranService {

    // INSERT =====================================================
    public boolean insert(JenisPembayaran jp) {
        String sql = "INSERT INTO JENIS_PEMBAYARAN (id_jenis, nama_jenis, keterangan) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jp.getIdJenis());
            ps.setString(2, jp.getNamaJenis());
            ps.setString(3, jp.getKeterangan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE =====================================================
    public boolean update(JenisPembayaran jp) {
        String sql = "UPDATE JENIS_PEMBAYARAN SET nama_jenis=?, keterangan=? WHERE id_jenis=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, jp.getNamaJenis());
            ps.setString(2, jp.getKeterangan());
            ps.setString(3, jp.getIdJenis());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE =====================================================
    public boolean delete(String id) {
        String sql = "DELETE FROM JENIS_PEMBAYARAN WHERE id_jenis = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID ==================================================
    public JenisPembayaran getById(String id) {
        String sql = "SELECT * FROM JENIS_PEMBAYARAN WHERE id_jenis = ?";
        JenisPembayaran jp = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                jp = new JenisPembayaran(
                        rs.getString("id_jenis"),
                        rs.getString("nama_jenis"),
                        rs.getString("keterangan")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jp;
    }

    // GET ALL ====================================================
    public List<JenisPembayaran> getAll() {
        List<JenisPembayaran> list = new ArrayList<>();
        String sql = "SELECT * FROM JENIS_PEMBAYARAN ORDER BY id_jenis";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                JenisPembayaran jp = new JenisPembayaran(
                        rs.getString("id_jenis"),
                        rs.getString("nama_jenis"),
                        rs.getString("keterangan")
                );
                list.add(jp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // CEK ID =====================================================
    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM JENIS_PEMBAYARAN WHERE id_jenis = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE ALL ==================================================
    public boolean deleteAll() {
        String sql = "DELETE FROM JENIS_PEMBAYARAN";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
