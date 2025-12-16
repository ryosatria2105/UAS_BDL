package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IdGenerator {

    public static String generateId(Connection conn, String table, String column, String prefix) {
        try {
            String sql = "SELECT " + column + " FROM " + table +
                         " ORDER BY " + column + " DESC LIMIT 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String lastId = rs.getString(1);    // contoh: PJ007
                int number = Integer.parseInt(lastId.substring(prefix.length()));
                number++;
                return prefix + String.format("%03d", number); // PJ008
            } else {
                return prefix + "001";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return prefix + "001";
        }
    }
}
