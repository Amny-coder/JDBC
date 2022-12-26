package com.fullstack;

import java.sql.*;

public class PreParedStatement {
    public void preStm() {
        String url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";

        try(Connection conn = DriverManager.getConnection(url);) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM person");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(7));
            }

        } catch (SQLException e) {
            System.err.println("Message: " + e.getMessage());
        }
    }
}
