package com.fullstack;

import java.sql.*;

public class MyJDBC {
    public final String url = "jdbc:postgresql://localhost:5432/test";
    private final String user = "postgres";
    private final String password = "661452";

    public void connector() {
        try(Connection conn = DriverManager.getConnection(url, user, password);) {
            if (conn != null) {
                System.out.println("Connected!");
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM car");
                while (rs.next()) {
                    double price = rs.getDouble("PRICE");
                    System.out.println("Final price: " + price);
                }
            }
            else
                System.out.println("Not connected!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
