package com.morlimoore.DAO;

import java.sql.*;

import com.morlimoore.models.User;
import com.morlimoore.utils.CloseConnection;


public class RegisterDAO {

    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static ResultSet myRs = null;

    public static void addUser(User newUser) throws SQLException, ClassNotFoundException {
        PreparedStatement myStmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");

            String sql = "INSERT INTO users" + "(fName, lName, email, hash, dateOfBirth, gender, createdAt) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, newUser.getfName());
            myStmt.setString(2, newUser.getlName());
            myStmt.setString(3, newUser.getEmail());
            myStmt.setString(4, newUser.getHash());
            myStmt.setString(5, newUser.getDateOfBirth());
            myStmt.setString(6, newUser.getGender());
            myStmt.setString(7, newUser.getCreatedAt());

            myStmt.execute();
        } finally {
            CloseConnection.close(myConn, myStmt, null);
        }
    }

//    public static void checkIfEmailExists(String email) {
//
//        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
//            String sql = "SELECT * FROM users WHERE email = " + email;
//            myStmt = myConn.createStatement();
//            myRs = myStmt.executeQuery(sql);
//            String userID = myRs.getString("email");
//            System.out.println("email: " + userID);
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            CloseConnection.close(myConn, myStmt, myRs);
//        }
//    }

}
