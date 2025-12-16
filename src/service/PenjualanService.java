package service;

import model.Penjualan;
import util.DBConnection;
import util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PenjualanService {

    // ================================
    // INSERT TRANSAKSI PENJUALAN
    // ================================
    public String insert(Penjualan p) {
        try (Connection conn = DBConnection.getConnection()) {

            String idBaru = IdGenerator.generateId(conn,
                    "penjualan", "id_penjualan", "PJ");
            p.setIdPenjualan(idBaru);

            String sql = "INSERT INTO penjualan " +
                    "(id_penjualan, id_pegawai, id_pembeli, tanggal, total, status_pembayaran) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getIdPenjualan());
            ps.setString(2, p.getIdPegawai());
            ps.setString(3, p.getIdPembeli());
            ps.setDate(4, java.sql.Date.valueOf(
                    p.getTanggal() != null ? p.getTanggal() : LocalDate.now()
            ));
            ps.setDouble(5, p.getTotal());
            ps.setString(6, p.getStatusPembayaran());

            ps.executeUpdate();
            return p.getIdPenjualan();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================================
    // RIWAYAT PENJUALAN (JOIN TABLE)
    // ================================
    public List<Object[]> getRiwayatPenjualan() {
        List<Object[]> data = new ArrayList<>();

        String sql =
            "SELECT p.id_penjualan, " +
            "       pg.nama_pegawai AS kasir, " +
            "       pb.nama_pembeli AS pembeli, " +
            "       jp.nama_jenis AS metode, " +
            "       p.total, " +
            "       py.jumlah_bayar, " +
            "       (py.jumlah_bayar - p.total) AS kembalian, " +
            "       p.tanggal " +
            "FROM penjualan p " +
            "LEFT JOIN pegawai pg ON p.id_pegawai = pg.id_pegawai " +
            "LEFT JOIN pembeli pb ON p.id_pembeli = pb.id_pembeli " +
            "LEFT JOIN pembayaran py ON p.id_penjualan = py.id_penjualan " +
            "LEFT JOIN jenis_pembayaran jp ON py.id_jenis = jp.id_jenis " +
            "ORDER BY p.tanggal DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(new Object[]{
                    rs.getString("id_penjualan"),
                    rs.getString("kasir"),
                    rs.getString("pembeli"),
                    rs.getString("metode"),
                    rs.getDouble("total"),
                    rs.getDouble("jumlah_bayar"),
                    rs.getDouble("kembalian"),
                    rs.getDate("tanggal")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
