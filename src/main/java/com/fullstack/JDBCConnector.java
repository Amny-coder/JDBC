package com.fullstack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    private Connection con = null;
    public void connector(String url) {
        if (url != null) {
            try {
                con = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println("Failed to connect --> ");
                e.printStackTrace();
            }
        }
    }

    public Connection getCon() {
        return con;
    }
}
