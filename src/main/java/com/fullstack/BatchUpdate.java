package com.fullstack;

import java.sql.*;
import java.util.Arrays;

public class BatchUpdate {

    public void batch() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";
        Connection conn = DriverManager.getConnection(url);

        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            stmt.addBatch("INSERT INTO car" +
                    "VALUES(uuid_generate_v4(), 'BMW', 'BM230', '1859.098')");
            stmt.addBatch("INSERT INTO car" +
                    "VALUES(uuid_generate_v4(), 'Toyota', 'Hilux', '185.83')");

            int[] batchCountUpdate = stmt.executeBatch();
            System.out.println(Arrays.toString(batchCountUpdate));
            conn.commit();

        } catch(BatchUpdateException e) {
            e.getLocalizedMessage();
        } finally {
            conn.setAutoCommit(true);
        }
    }

}
