package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Restock;
import util.DBConnection;

public class RestockService {

    // ---------- GENERATE ID ----------
    public String generateIdRestock() {
        String prefix = "RS"; // SESUAIKAN DENGAN FORMAT ID MU
        String sql = "SELECT id_restock FROM restock ORDER BY id_restock DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastId = rs.getString(1);
                String numPart = lastId.replace(prefix, "");
                int num = Integer.parseInt(numPart);
                num++;
                return String.format(prefix + "%03d", num);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prefix + "001";
    }

    // ---------- INSERT HEADER RESTOCK ----------
    public boolean insert(Restock r) {
        String sql = "INSERT INTO restock (id_restock, id_pegawai, id_supplier, tanggal, jumlah) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getIdRestock());
            ps.setString(2, r.getIdPegawai());
            ps.setString(3, r.getIdSupplier());
            ps.setDate(4, r.getTanggal());
            ps.setInt(5, r.getJumlah());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------- GET SATU RESTOCK BY ID ----------
    public Restock getById(String idRestock) {
        String sql = "SELECT r.id_restock, r.id_pegawai, p.nama_pegawai, "
                   + "       r.id_supplier, s.nama_supplier, r.tanggal, r.jumlah "
                   + "FROM restock r "
                   + "JOIN pegawai p ON r.id_pegawai = p.id_pegawai "
                   + "JOIN supplier s ON r.id_supplier = s.id_supplier "
                   + "WHERE r.id_restock = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idRestock);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Restock r = new Restock();
                    r.setIdRestock(rs.getString("id_restock"));
                    r.setIdPegawai(rs.getString("id_pegawai"));
                    r.setNamaPegawai(rs.getString("nama_pegawai"));
                    r.setIdSupplier(rs.getString("id_supplier"));
                    r.setNamaSupplier(rs.getString("nama_supplier"));
                    r.setTanggal(rs.getDate("tanggal"));
                    r.setJumlah(rs.getInt("jumlah"));
                    return r;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ---------- LIST UNTUK TABEL RESTOCK (JOIN + OBJECT[]) ----------
    public List<Object[]> getRiwayatRestock() {
        List<Object[]> list = new ArrayList<>();

        String sql = "SELECT r.id_restock, p.nama_pegawai, s.nama_supplier, "
                   + "       r.jumlah, r.tanggal "
                   + "FROM restock r "
                   + "JOIN pegawai p ON r.id_pegawai = p.id_pegawai "
                   + "JOIN supplier s ON r.id_supplier = s.id_supplier "
                   + "ORDER BY r.tanggal DESC, r.id_restock DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getString("id_restock"),
                        rs.getString("nama_pegawai"),
                        rs.getString("nama_supplier"),
                        rs.getInt("jumlah"),
                        rs.getDate("tanggal")
                };
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ---------- HAPUS SATU RESTOCK (HEADER + DETAIL) ----------
    public boolean delete(String idRestock) {
        String sqlDetail = "DELETE FROM detail_restock WHERE id_restock = ?";
        String sqlHeader = "DELETE FROM restock WHERE id_restock = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                 PreparedStatement psHeader = conn.prepareStatement(sqlHeader)) {

                // hapus detail dulu (FK ON DELETE RESTRICT)
                psDetail.setString(1, idRestock);
                psDetail.executeUpdate();

                // hapus header
                psHeader.setString(1, idRestock);
                int affected = psHeader.executeUpdate();

                conn.commit();
                return affected > 0;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------- CLEAR ALL RESTOCK + DETAIL ----------
    public boolean deleteAll() {
        String sqlDetail = "DELETE FROM detail_restock";
        String sqlHeader = "DELETE FROM restock";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                 PreparedStatement psHeader = conn.prepareStatement(sqlHeader)) {

                psDetail.executeUpdate();
                psHeader.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
