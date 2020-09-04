package com.morlimoore.DAO;

import com.morlimoore.models.User;

import java.sql.*;

public class UserDetailsDAO {

    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static ResultSet myRs = null;

    public static User getUserDetailsFromDB(String email) {
        User tempUser = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            String sql = "SELECT * FROM users WHERE email = " + email + ";";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()) {
                tempUser.setUserID(myRs.getInt("userID"));
                tempUser.setfName(myRs.getString("fName"));
                tempUser.setlName(myRs.getString("lName"));
                tempUser.setEmail(myRs.getString("email"));
                tempUser.setDateOfBirth(myRs.getString("dateOfBirth"));
                tempUser.setGender(myRs.getString("gender"));
                tempUser.setCreatedAt(myRs.getString("createdAt"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return tempUser;
    }

    public static User getUserFromDBbyID(int id) throws ClassNotFoundException, SQLException {
        User tempUser = new User();
        Class.forName("com.mysql.cj.jdbc.Driver");
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
        String sql = "SELECT * FROM users WHERE userID =" + id;
        myStmt = myConn.createStatement();
        myRs = myStmt.executeQuery(sql);
        while(myRs.next()) {
            tempUser.setUserID(myRs.getInt("userID"));
            tempUser.setfName(myRs.getString("fName"));
            tempUser.setlName(myRs.getString("lName"));
            tempUser.setEmail(myRs.getString("email"));
            tempUser.setDateOfBirth(myRs.getString("dateOfBirth"));
            tempUser.setGender(myRs.getString("gender"));
            tempUser.setCreatedAt(myRs.getString("createdAt"));
        }
        return tempUser;
    }
}
