package com.fullstack;

import javax.naming.NamingException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws NamingException, SQLException {
        JDBCRowSet jdbcRowSet = new JDBCRowSet();
        jdbcRowSet.insertion();
    }
}