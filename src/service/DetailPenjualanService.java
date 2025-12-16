package service;

import model.DetailPenjualan;
import util.DBConnection;
import util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DetailPenjualanService {

    public void insert(DetailPenjualan d) {
        try (Connection conn = DBConnection.getConnection()) {

            String idBaru = IdGenerator.generateId(conn,
                    "detail_penjualan", "id_detail_penjualan", "DP");
            d.setIdDetailPenjualan(idBaru);

            String sql = "INSERT INTO detail_penjualan " +
                    "(id_detail_penjualan, id_penjualan, id_barang, jumlah, subtotal) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getIdDetailPenjualan());
            ps.setString(2, d.getIdPenjualan());
            ps.setString(3, d.getIdBarang());
            ps.setInt(4, d.getJumlah());
            ps.setDouble(5, d.getSubtotal());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
