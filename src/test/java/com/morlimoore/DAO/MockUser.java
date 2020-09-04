package com.morlimoore.DAO;

import com.morlimoore.models.User;
import com.morlimoore.utils.BCrypt;

import java.sql.SQLException;

public class MockUser {

    public static User getUser() {
        String fName = "John";
        String lName = "Nwaefi";
        String email = "johnNwaefi@gmail.com";
        String password = "john123";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        String dateOfBirth = "1998-09-01 00:00:00";
        String gender = "male";
        String createdAt = "2020-09-04 10:48:36";
        User tempUser = new User(fName, lName, email, hash, dateOfBirth, gender, createdAt);
        return tempUser;
    }

    public static User getUserFromDB() {
        User tempUser = new User();
        try {
            RegisterDAO.addUser(getUser());
            tempUser = UserDetailsDAO.getUserDetailsFromDB(getUser().getEmail());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tempUser;
    }
}
