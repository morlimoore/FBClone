package com.morlimoore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetDBConnection {

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
        return myConn;
    }
}
