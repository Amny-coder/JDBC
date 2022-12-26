package com.fullstack;

import java.sql.*;

public class Query {
    public void retriveData() {
        String url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=661452";
        String query = "SELECT * FROM car";
        String updateQuery = "INSERT INTO car(car_uid, make, model, price) " +
                "VALUES(uuid_generate_v4(), BMW, X3, 1859.098";

        String formatter = "\t\t\t\t\t\t\t\t\t\t";
        System.out.println("CarID" + formatter + "Make\t Model\t Price");
        System.out.println();

        try(Connection conn = DriverManager.getConnection(url);) {
            Statement stmt = conn.createStatement();
            //int rs = stmt.executeUpdate(updateQuery);
            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {
                String carId = resultSet.getString("car_uid");
                String carMake = resultSet.getString("make");
                String carModel = resultSet.getString("model");
                String carPrice = resultSet.getString("price");

                System.out.println(carId + "\t" + carMake + "\t" + carModel + "\t" + carPrice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
