package com.morlimoore.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CloseConnection {

    public static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myConn != null) {
                myConn.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myRs != null) {
                myRs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
