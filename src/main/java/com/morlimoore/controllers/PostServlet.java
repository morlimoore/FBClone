package com.morlimoore.controllers;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.morlimoore.models.User;
import com.morlimoore.DAO.PostsDAO;

/**
 * This sends the user posts to the database
 */

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
//    PostsDAO postsDAO = new PostsDAO();

    @Resource(name="jdbc/fbClone")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
//        try {
            PostsDAO.addPostToDB(user, text);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }


}
