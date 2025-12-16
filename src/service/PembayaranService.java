package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class PembayaranService {

    public List<Object[]> getHistori() {
    List<Object[]> data = new ArrayList<>();

    String sql = 
        "SELECT py.id_pembayaran, " +
        "       py.id_penjualan, " +
        "       pb.nama_pembeli, " +
        "       jp.nama_jenis AS metode, " +
        "       pj.total, " +
        "       py.jumlah_bayar, " +
        "       (py.jumlah_bayar - pj.total) AS kembalian, " +
        "       py.tanggal_pembayaran " +
        "FROM pembayaran py " +
        "JOIN penjualan pj ON py.id_penjualan = pj.id_penjualan " +
        "JOIN pembeli pb ON pj.id_pembeli = pb.id_pembeli " +
        "JOIN jenis_pembayaran jp ON py.id_jenis = jp.id_jenis " +
        "ORDER BY py.tanggal_pembayaran DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            data.add(new Object[]{
                rs.getString("id_pembayaran"),
                rs.getString("id_penjualan"),
                rs.getString("nama_pembeli"),
                rs.getString("metode"),
                rs.getDouble("total"),
                rs.getDouble("jumlah_bayar"),
                rs.getDouble("kembalian"),
                rs.getDate("tanggal_pembayaran")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return data;
}
    
    public boolean existsById(String id) {
    String sql = "SELECT COUNT(*) FROM pembayaran WHERE id_pembayaran = ?";
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

    public boolean delete(String id) {
    String sql = "DELETE FROM pembayaran WHERE id_pembayaran = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, id);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


public boolean deleteAll() {
    String sql = "DELETE FROM pembayaran";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}



}
