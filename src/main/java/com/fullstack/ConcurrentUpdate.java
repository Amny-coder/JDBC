package com.fullstack;

import java.sql.*;

public class ConcurrentUpdate {

    public void updatePrices() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";

        Connection conn = DriverManager.getConnection(url);

        try(Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Connected!\n");
            ResultSet rs = stm.executeQuery("SELECT * FROM car");
            
            double price = 0;
            while (rs.next()) {
                price = rs.getDouble("PRICE");

            }
            rs.updateDouble("PRICE", price * 3);
            rs.updateRow();

        } catch (Exception e) {e.printStackTrace();}
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Message: " + ex);
                }
            }
        }
    }
}
