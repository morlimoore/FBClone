package com.morlimoore.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.morlimoore.DAO.LoginDAO;
import com.morlimoore.utils.BCrypt;

/**
 * This checks if the user email exists in the database and also
 * if the provided password matches the stored hash in the database.
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //If successful, it navigates to the dashboard, else it remains on the homepage
        try {
            if (LoginDAO.checkIfEmailExistsInDb(email) && (BCrypt.checkpw(password, LoginDAO.getUserHash(email)))) {
                System.out.println("Login successful");
                System.out.println("user=> " + LoginDAO.getUserFromDB(email));
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("user", LoginDAO.getUserFromDB(email));

                request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("/views/welcomePage.jsp").forward(request, response);
                System.out.println("Login unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
