package com.morlimoore.controllers;

import com.morlimoore.DAO.CommentsDAO;
import com.morlimoore.models.Post;
import com.morlimoore.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("comment");
        HttpSession session = request.getSession();

        //Retrieves user object from the session storage
        User tempUser = (User) session.getAttribute("user");

        int userID = tempUser.getUserID();
        int postID = Integer.parseInt(request.getParameter("postID"));

        try {
            //Adds comments to the database
            CommentsDAO.addComment(message, userID, postID);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
