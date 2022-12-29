package com.fullstack;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws NamingException, SQLException {

        Transaction transaction = new Transaction();
        HashMap<String, Integer> db = new HashMap<>();

        db.put("German-coff", 15);
        db.put("Local-coff", 10);
        transaction.updateCoffeeSales(db);
    }
}