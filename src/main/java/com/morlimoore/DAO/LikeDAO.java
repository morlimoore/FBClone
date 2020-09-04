package com.morlimoore.DAO;

import com.morlimoore.utils.CloseConnection;

import java.sql.*;

public class LikeDAO {

    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static ResultSet myRs = null;

    public static void incrementLike(String type, int ID, int userID) throws ClassNotFoundException, SQLException {
        PreparedStatement myStmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");

        String sql = null;
        if(type.equals("comment")) {
            sql = "UPDATE comments SET likes = likes + 1 WHERE commentID = ? AND userID = ?;";
        } else if(type.equals("post")) {
            sql = "UPDATE posts SET likes = likes + 1 WHERE postID = ? AND userID = ?;";
        }

        myStmt = myConn.prepareStatement(sql);

        myStmt.setInt(1, ID);
        myStmt.setInt(2, userID);

        myStmt.execute();
        CloseConnection.close(myConn, myStmt, null);
    }

    public static void decrementLike(int postID, int userID) throws ClassNotFoundException, SQLException {
        PreparedStatement myStmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");

        String sql = "UPDATE posts SET likes = likes - 1 WHERE postID = ? AND userID = ?;";

        myStmt = myConn.prepareStatement(sql);

        myStmt.setInt(1, postID);
        myStmt.setInt(2, userID);

        myStmt.execute();
        CloseConnection.close(myConn, myStmt, null);
    }
}
