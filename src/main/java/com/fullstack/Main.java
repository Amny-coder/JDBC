package com.fullstack;

import javax.naming.NamingException;

public class Main {
    public static void main(String[] args) throws NamingException {
        
        TableUpdate update = new TableUpdate();
        update.updateTableDB();
    }
}