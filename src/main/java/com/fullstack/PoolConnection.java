package com.fullstack;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {
    public void connectionPoolCreation() throws NamingException {
        try {
            Context initCtx = new InitialContext();

            if (initCtx == null)
                throw new  Exception("Boom - no Context");
            DataSource dataSource = (DataSource) initCtx.lookup("");

            Connection conn = dataSource.getConnection();
            if (conn != null)
                System.out.print("Connection successful!" + conn.toString());
            else
                System.out.println("Oops connection failed");

           // conn.close();
        } catch (Exception e) {
            System.err.println("Message " + e.getMessage() + "\nCause " + e.getCause());
        }


    }

}