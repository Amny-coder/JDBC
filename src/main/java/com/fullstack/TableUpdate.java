package com.fullstack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableUpdate {

    public void updateTableDB() {
        String url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";
        String query = "INSERT INTO car " +
                "VALUES(uuid_generate_v4(), 'Toyota', 'Corola-S', '1200000')";
        String updateQuery = "INSERT INTO car VALUES(uuid_generate_v4(), 'BMW', 'X3', '1859.098')";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stm = conn.createStatement();

            int affectedRows;
            affectedRows = stm.executeUpdate(updateQuery);
            affectedRows += stm.executeUpdate(query);
            System.out.println(affectedRows + " Affected");
        } catch (SQLException e) {
            System.err.println("Message: " + e.getMessage() + "\nCause: " + e.getCause());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                System.out.println("Failed to closed connection in " + getClass() + " class");
            }

        }
    }
}
