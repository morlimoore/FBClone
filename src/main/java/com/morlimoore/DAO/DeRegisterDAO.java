package com.morlimoore.DAO;

import com.morlimoore.utils.GetDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * For the deregistration, we simply delete a users record from the database
 */
public class DeRegisterDAO {

    private static Connection myConn = null;
    private static PreparedStatement myStmt = null;

    public static int deleteUser(String email) throws ClassNotFoundException, SQLException {
        myConn = GetDBConnection.connect();
        String sql = "DELETE FROM users WHERE email = ?;";
        myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, email);
        int result = myStmt.executeUpdate();
        return result;
    }
}
