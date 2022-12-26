package com.fullstack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJDBC {
    public final String url = "jdbc:postgresql://localhost:5432/test";
    private final String user = "postgres";
    private final String password = "661452";

    public void connector() {
        try(Connection conn = DriverManager.getConnection(url, user, password);) {
            if (conn != null)
                System.out.println("Connected!");
            else
                System.out.println("Not connected!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
