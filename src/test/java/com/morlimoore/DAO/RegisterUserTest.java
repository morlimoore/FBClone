package com.morlimoore.DAO;

import com.morlimoore.models.User;
import com.morlimoore.utils.BCrypt;
import com.morlimoore.utils.GetDBConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpSession;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserTest {

    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;

    @Test
    @DisplayName("Can add user in DB")
    void addUser() {
        User mockUser = MockUser.getUser();
        try {
            RegisterDAO.addUser(mockUser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Query and compare user data sent to database
        try {
//            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fb_clone_db", "root", "vikkidchamp");
            myConn = GetDBConnection.connect();
            String sql = "SELECT * FROM users WHERE email = ?;";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, mockUser.getEmail());
            myRs = myStmt.executeQuery();
            while(myRs.next()) {
                assertAll(
                        () -> assertTrue(myRs.getString("fName").equals(mockUser.getfName())),
                        () -> assertTrue(myRs.getString("lName").equals(mockUser.getlName())),
                        () -> assertTrue(myRs.getString("email").equals(mockUser.getEmail())),
                        () -> assertTrue(myRs.getString("dateOfBirth").equals(mockUser.getDateOfBirth())),
                        () -> assertTrue(myRs.getString("gender").equals(mockUser.getGender())),
                        () -> assertTrue(myRs.getString("createdAt").equals(mockUser.getCreatedAt()))
                );
            }

            //Deleting the user data sent to Database
            DeRegisterDAO.deleteUser(mockUser.getEmail());

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }


    }
}