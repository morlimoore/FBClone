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
 * This affords like functionality for both posts and comments
 */

@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String likeType = request.getParameter("likeType");
        if(likeType.equals("postsLike")) {
            int postID = Integer.parseInt(request.getParameter("postID"));
            int userID = Integer.parseInt(request.getParameter("userID"));

            try {
                LikeDAO.incrementLike("post", postID, userID);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if(likeType.equals("commentsLike")) {
            int commentID = Integer.parseInt(request.getParameter("commentID"));
            int userID = Integer.parseInt(request.getParameter("userID"));

            try {
                LikeDAO.incrementLike("comment", commentID, userID);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
