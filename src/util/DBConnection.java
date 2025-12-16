package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL  = "jdbc:postgresql://localhost:5432/db_uas_bdl";
    private static final String USER = "postgres";
    private static final String PASS = "masryoo21"; // ganti

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("❌ Gagal konek ke DB: " + e.getMessage());
            return null;
        }
    }
}
