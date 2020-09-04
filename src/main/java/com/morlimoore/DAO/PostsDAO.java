package com.morlimoore.DAO;

import com.morlimoore.models.Post;
import com.morlimoore.models.User;
import com.morlimoore.utils.CloseConnection;
import com.morlimoore.utils.GetDBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This handles all that has to do with user posts. Including getting all the posts
 * by a user, getting all the posts in the database and adding a post to the database.
 */
public class PostsDAO {

    private static Connection myConn = null;
    private static Statement myStmt = null;
    private static ResultSet myRs = null;

    public static List<String> getUserPosts(User user) throws ClassNotFoundException {
        List<String> userPosts = new ArrayList<>();
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            myConn = GetDBConnection.connect();
            String sql = "SELECT * FROM posts WHERE userID = " + user.getUserID();
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()) {
                userPosts.add(myRs.getString("message"));
            }
            System.out.println("list: " + userPosts);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPosts;
    }

    /**
     * Fetches all the posts in the database
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Post> getAllUsersPosts() throws ClassNotFoundException {
        Post tempPost = new Post();
        List<Post> allUsersPosts = new ArrayList<>();
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            myConn = GetDBConnection.connect();
            String sql = "SELECT * FROM posts;";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()) {
                tempPost.setPostID(myRs.getInt("postID"));
                tempPost.setMessage(myRs.getString("message"));
                tempPost.setUserID(myRs.getInt("userID"));
                tempPost.setLikes(myRs.getInt("likes"));
                tempPost.setCreatedAt(myRs.getString("createdAt"));
                allUsersPosts.add(tempPost);
                tempPost = new Post();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsersPosts;
    }

    /**
     * Adds posts by a user to the database
     * @param user
     * @param message
     */
    public static void addPostToDB(User user, String message) {
        PreparedStatement myStmt = null;
//        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            myConn = GetDBConnection.connect();
            String sql = "INSERT INTO posts" + "(message, userID, likes, createdAt) " + "VALUES (?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String currentDate = dtf.format(now);

            myStmt.setString(1, message);
            myStmt.setInt(2, user.getUserID());
            myStmt.setInt(3, 0);
            myStmt.setString(4, currentDate);

            myStmt.execute();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection.close(myConn, myStmt, myRs);
        }
    }
}
