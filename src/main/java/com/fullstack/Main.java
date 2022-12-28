package com.fullstack;

import javax.naming.NamingException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws NamingException, SQLException {

        BatchUpdate batch = new BatchUpdate();
        batch.batch();

        ConcurrentUpdate up = new ConcurrentUpdate();
        up.updatePrices();

        MyJDBC jdbc = new MyJDBC();
        jdbc.connector();
    }
}