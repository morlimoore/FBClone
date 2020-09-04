package com.morlimoore.controllers;

import com.morlimoore.DAO.LikeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * For the unlike functionality, I simply decremented the like value
 */

@WebServlet("/UnlikeServlet")
public class UnlikeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LikeDAO likeDAO = new LikeDAO();
        int postID = Integer.parseInt(request.getParameter("postID"));
        int userID = Integer.parseInt(request.getParameter("userID"));

        try {
            likeDAO.decrementLike(postID, userID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
