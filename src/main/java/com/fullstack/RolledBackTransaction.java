package com.fullstack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class RolledBackTransaction extends JDBCConnector {
    private final double MAXPRICE = 50.99;
    private static final String URL = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";

    RolledBackTransaction() {
        super();
        connector(URL);
    }
    public void increasedPriceByPercentage(String coffeeName, float pricePercentage) throws SQLException {
        String priceQuery = "SELECT cof_name, price FROM coffees" +
                "WHERE cof_name = ?";
        String updateQuery = "UPDATE coffees SET price = ? cof_name = ?";

        getCon().setAutoCommit(false);
        ResultSet rs = null;

        try(PreparedStatement price = getCon().prepareStatement(priceQuery, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
                PreparedStatement updatePrice = getCon().prepareStatement(updateQuery)) {

            Savepoint save1 = getCon().setSavepoint();

            price.setString(1, coffeeName);

            if (!price.execute()) {
                System.out.println("Could not find entry for the coffee named: " + coffeeName);
            }
            else {
                rs = price.getResultSet();
                rs.first();

                double oldPrice = rs.getDouble("PRICE");
                double newPrice = oldPrice + (oldPrice * pricePercentage);

                updatePrice.setDouble(1, newPrice);
                updatePrice.setString(2, "German-coff");

                if (newPrice > MAXPRICE) {
                    System.out.printf("The new price, $%.2f, is greater " +
                                    "than the maximum price, $%.2f. " +
                                    "Rolling back the transaction...%n",
                            newPrice, MAXPRICE);
                    getCon().rollback(save1);
                }
                getCon().commit();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
