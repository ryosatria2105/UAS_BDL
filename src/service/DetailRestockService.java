package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.DetailRestock;
import util.DBConnection;

public class DetailRestockService {

    // ---------- GENERATE ID DETAIL ----------
    public String generateIdDetailRestock() {
        String prefix = "DR"; // SESUAIKAN FORMAT ID
        String sql = "SELECT id_detail_restock FROM detail_restock "
                   + "ORDER BY id_detail_restock DESC LIMIT 1";

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

    // ---------- INSERT SATU DETAIL ----------
    public boolean insert(DetailRestock d) {
        String sql = "INSERT INTO detail_restock "
                   + "(id_detail_restock, id_restock, id_barang, jumlah, harga_beli) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getIdDetailRestock());
            ps.setString(2, d.getIdRestock());
            ps.setString(3, d.getIdBarang());
            ps.setInt(4, d.getJumlah());
            ps.setDouble(5, d.getHargaBeli());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------- LIST DETAIL (MODEL) BY ID_RESTOCK ----------
    public List<DetailRestock> getByRestock(String idRestock) {
        List<DetailRestock> list = new ArrayList<>();

        String sql = "SELECT dr.id_detail_restock, dr.id_restock, dr.id_barang, "
                   + "       b.nama_barang, dr.jumlah, dr.harga_beli "
                   + "FROM detail_restock dr "
                   + "JOIN barang b ON dr.id_barang = b.id_barang "
                   + "WHERE dr.id_restock = ? "
                   + "ORDER BY dr.id_detail_restock";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idRestock);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetailRestock d = new DetailRestock();
                    d.setIdDetailRestock(rs.getString("id_detail_restock"));
                    d.setIdRestock(rs.getString("id_restock"));
                    d.setIdBarang(rs.getString("id_barang"));
                    d.setNamaBarang(rs.getString("nama_barang"));
                    d.setJumlah(rs.getInt("jumlah"));
                    d.setHargaBeli(rs.getDouble("harga_beli"));
                    list.add(d);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ---------- LIST DETAIL UNTUK TABEL (Object[]) ----------
    public List<Object[]> getTableByRestock(String idRestock) {
        List<Object[]> list = new ArrayList<>();

        String sql = "SELECT dr.id_detail_restock, dr.id_barang, b.nama_barang, "
                   + "       dr.jumlah, dr.harga_beli, "
                   + "       (dr.jumlah * dr.harga_beli) AS subtotal "
                   + "FROM detail_restock dr "
                   + "JOIN barang b ON dr.id_barang = b.id_barang "
                   + "WHERE dr.id_restock = ? "
                   + "ORDER BY dr.id_detail_restock";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idRestock);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[] {
                            rs.getString("id_detail_restock"),
                            rs.getString("id_barang"),
                            rs.getString("nama_barang"),
                            rs.getInt("jumlah"),
                            rs.getDouble("harga_beli"),
                            rs.getDouble("subtotal")
                    };
                    list.add(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ---------- HAPUS DETAIL BERDASARKAN ID_RESTOCK ----------
    public boolean deleteByRestock(String idRestock) {
        String sql = "DELETE FROM detail_restock WHERE id_restock = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idRestock);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------- CLEAR ALL DETAIL ----------
    public boolean deleteAll() {
        String sql = "DELETE FROM detail_restock";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
}
