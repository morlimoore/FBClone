package com.morlimoore.DAO;

import com.morlimoore.utils.GetDBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PostsDAOTest {

    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;

    @Test
    void getUserPosts() {
    }

    @Test
    void getAllUsersPosts() {
    }

    @Test
    void addPostToDB() {
        String message = "Hello, this is the test post.";
        PostsDAO.addPostToDB(MockUser.getUserFromDB(), message);

        //Query and compare post data sent to database
        try {
            myConn = GetDBConnection.connect();
            String sql = "SELECT * FROM posts WHERE userID = ? AND message = ?;";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, MockUser.getUserFromDB().getUserID());
            myStmt.setString(2, message);
            myRs = myStmt.executeQuery();
            while(myRs.next()) {
                assertAll(
                        () -> assertTrue(myRs.getInt("userID") == MockUser.getUserFromDB().getUserID()),
                        () -> assertTrue(myRs.getString("message").equals(message))
                );
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}