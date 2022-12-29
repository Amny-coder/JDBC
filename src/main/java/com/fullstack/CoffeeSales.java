package com.fullstack;

import java.sql.*;

public class CoffeeSales extends JDBCConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";

    CoffeeSales() {
        super();
        connector(URL);
    }

    public void coffeesTable() {

        String itExists = "SELECT COUNT(*) FROM information_schema.tables WHERE TABLE_NAME = 'coffees'" +
                "LIMIT 1";
        String createTable = "IF TACREATE TABLE coffees(cof_name VARCHAR(30) NOT NULL, sales BIGINT NOT NULL, " +
                "total NUMERIC(9, 3) )";
        String insertQuery = "INSERT INTO coffees VALUES('German-Coff', 23, 0)";

        try(PreparedStatement preStm = getCon().prepareStatement(createTable);
                PreparedStatement insertStm = getCon().prepareStatement(insertQuery);
                Statement stm =  getCon().createStatement()) {

            ResultSet resultSet = stm.executeQuery(itExists);
            resultSet.next();
            int exists = resultSet.getInt(1);

            if (exists != 0) {
                System.out.println("Table already exists!");
            } else {
                ResultSet rs = preStm.executeQuery();
                int rowsAffected = insertStm.executeUpdate();
                System.out.println(rowsAffected + " Rows affected");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (getCon() != null)
                    getCon().close();
            } catch (SQLException ignored) {}
        }
    }
}
