package com.fullstack;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Transaction extends JDBCConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";

    Transaction() {
        super();
        connector(URL);
    }

    public void updateCoffeeSales(HashMap<String, Integer> list) {
        String sales = "UPDATE coffees SET sales = ? WHERE cof_name = ?";
        String total = "UPDATE coffees SET total = + ? WHERE cof_name = ?";

        try(PreparedStatement updateSales = getCon().prepareStatement(sales);
                PreparedStatement updateTotal = getCon().prepareStatement(total)) {
            getCon().setAutoCommit(false);

            for (Map.Entry<String, Integer> e : list.entrySet()) {
                updateSales.setInt(1, e.getValue());
                updateSales.setString(2, e.getKey());
                updateSales.executeUpdate();

                updateTotal.setInt(1, e.getValue());
                updateTotal.setString(2, e.getKey());
                updateTotal.executeUpdate();

                getCon().commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (getCon() != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    getCon().rollback();
                }catch (SQLException ignored) {}

            }
        } catch (IllegalStateException e) {
            e.getCause();
        }
    }
}
