package com.workWithUs.controller.servlets.common.userEdit;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.UserDAO;
import com.workWithUs.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "avatarEdit", value = "/avatarEdit")
public class AvatarEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        String avatar = request.getParameter("avatar");

        try (Connection connection = connectionPool.getConnection()){
            User user = userDAO.getUser(id,connection);
            user.setAvatar(avatar);
            userDAO.updateUser(user,connection);

        } catch (SQLException e) {
                    e.printStackTrace();
        }

        response.sendRedirect("profile");
    }
}
