package com.fullstack;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class JDBCRowSet extends JDBCConnector {

    public JDBCRowSet() {
        super();
    }

    public void insertion() throws SQLException {
        RowSetFactory factory = RowSetProvider.newFactory();

        try(JdbcRowSet jdbc = factory.createJdbcRowSet();) {
            jdbc.setUrl("jdbc:postgresql://localhost:5432/test");
            jdbc.setUsername("postgres");
            jdbc.setPassword("661452");
            jdbc.setCommand("SELECT * FROM coffees");
            jdbc.execute();

            jdbc.moveToInsertRow();
            jdbc.updateString("COF_NAME", "England-coff");
            jdbc.updateInt("PRICE", 10);
            jdbc.insertRow();

            /*while (jdbc.next()) {
                String coffName = jdbc.getString("COF_NAME");
                int price = jdbc.getInt("PRICE");
                double getTotal = jdbc.getDouble("TOTAL");

                System.out.println(coffName + " " + price + " " + getTotal);
            }*/

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
