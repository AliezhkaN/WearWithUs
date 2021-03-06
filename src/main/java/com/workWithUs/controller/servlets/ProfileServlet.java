package com.workWithUs.controller.servlets;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.UserDAO;
import com.workWithUs.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "profile", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();

        int id = (int) session.getAttribute("userId");
        try (Connection connection = connectionPool.getConnection()){
            User user = userDAO.getUser(id,connection);
            request.setAttribute("user",user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
