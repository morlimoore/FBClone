package com.morlimoore.DAO;

import com.morlimoore.models.Comment;
import com.morlimoore.models.User;
import com.morlimoore.utils.CloseConnection;
import com.morlimoore.utils.GetDBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This handles everything about sending comments to the database,
 * getting comments and the commenters.
 */
public class CommentsDAO {

    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static ResultSet myRs = null;

    public static void addComment(String message, int userID, int postID) throws SQLException, ClassNotFoundException {
        PreparedStatement myStmt = null;

//        Class.forName("com.mysql.cj.jdbc.Driver");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

//        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
        myConn = GetDBConnection.connect();
        String sql = "INSERT INTO comments" + "(message, userID, postID, likes, createdAt) " + "VALUES (?, ?, ?, ?, ?)";
        myStmt = myConn.prepareStatement(sql);

        myStmt.setString(1, message);
        myStmt.setInt(2, userID);
        myStmt.setInt(3, postID);
        myStmt.setInt(4, 0);
        myStmt.setString(5, currentDate);

        myStmt.execute();
        CloseConnection.close(myConn, myStmt, myRs);
    }

    public static List<Comment> getCommentsUnderPost(int postID) {
        Comment tempComment = new Comment();
        List<Comment> commentList = new ArrayList<>();
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            String sql = "SELECT * FROM comments WHERE postID = " + postID;
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()) {
                tempComment.setCommentID(myRs.getInt("commentID"));
                tempComment.setMessage(myRs.getString("message"));
                tempComment.setUserID(myRs.getInt("userID"));
                tempComment.setPostID(myRs.getInt("postID"));
                tempComment.setLikes(myRs.getInt("likes"));
                tempComment.setCreatedAt(myRs.getString("createdAt"));
                commentList.add(tempComment);
                tempComment = new Comment();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.close(myConn, myStmt, myRs);
        }
        return commentList;
    }


    public static User getCommenter(int commentID, int postID) {
        User tempUser = new User();
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            String sql = "SELECT fName, lName, postID FROM users JOIN comments ON comments.userID = users.userID " +
                    "WHERE comments.commentID = " + commentID + " AND " + "comments.postID = " + postID;
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()) {
                tempUser.setfName(myRs.getString("fName"));
                tempUser.setlName(myRs.getString("lName"));
                tempUser.setPostID(myRs.getInt("postID"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.close(myConn, myStmt, myRs);
        }
        return tempUser;
    }
}
