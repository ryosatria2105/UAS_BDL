package util;


import util.DBConnection;
import java.sql.Connection;

public class TestKoneksi {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("✅ Koneksi ke database berhasil!");
        } else {
            System.out.println("❌ Koneksi ke database gagal!");
        }
    }
}
