package com.morlimoore.DAO;

import com.morlimoore.models.User;
import com.morlimoore.utils.CloseConnection;
import com.morlimoore.utils.GetDBConnection;

import java.io.PrintWriter;
import java.sql.*;

public class LoginDAO {

    private static PrintWriter out = null;
    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static ResultSet myRs = null;

    public static Boolean checkIfEmailExistsInDb(String email) throws ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            myConn = GetDBConnection.connect();
            String sql = "SELECT email FROM users WHERE email = " + "'" + email + "'" + ";";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            String result = null;

            while(myRs.next()) {
                result = myRs.getString("email");
            }

            if(result.equals(email)) {
                return true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.close(myConn, myStmt, myRs);
        }
        return false;
    }

    public static User getUserFromDB(String email) throws ClassNotFoundException, SQLException {
        User tempUser = null;
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
        myConn = GetDBConnection.connect();
        String sql = "SELECT * FROM users WHERE email = " + "'" + email + "'" + ";";
        myStmt = myConn.createStatement();
        myRs = myStmt.executeQuery(sql);

        while(myRs.next()) {
            int userID = myRs.getInt("userID");
            String fName = myRs.getString("fName");
            String lName = myRs.getString("lName");
            String dateOfBirth = myRs.getString("dateOfBirth");
            String gender = myRs.getString("gender");
            String createdAt = myRs.getString("createdAt");

            tempUser = new User(userID, fName, lName, email, dateOfBirth, gender, createdAt);
        }
        return tempUser;
    }

    public static String getUserHash(String email) throws ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        String result = null;
        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            myConn = GetDBConnection.connect();
            String sql = "SELECT hash FROM users WHERE email = " + "'" + email + "'" + ";";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while(myRs.next()) {
                result = myRs.getString("hash");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
