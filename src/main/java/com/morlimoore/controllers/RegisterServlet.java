package com.morlimoore.controllers;

import com.morlimoore.DAO.RegisterDAO;
import com.morlimoore.utils.BCrypt;
import com.morlimoore.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This creates a user object from the user inputted parameters
 * and sends it to the database.
 */

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private Boolean isErrorThrown = false;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");

        //Translates the user entered date to an SQL compatible format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User(fName, lName, email, hash, dateOfBirth, gender, currentDate);

        try {
            RegisterDAO.addUser(newUser);
        } catch(Exception e) {
            isErrorThrown = true;
            System.out.println(e);
        }

        if(isErrorThrown) {
                request.getRequestDispatcher("/views/welcomePage.jsp").forward(request, response);
                System.out.println("Email already exists");
                isErrorThrown = false;
        } else {
            //Registration successful popup
                request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
                System.out.println("Registration successful");
        }
    }
}
