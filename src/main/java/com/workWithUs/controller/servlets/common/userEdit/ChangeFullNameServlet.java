package com.workWithUs.controller.servlets.common.userEdit;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.UserDAO;
import com.workWithUs.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ChangeFullNameServlet -> used for changing full name in personal profile
 *
 * @author Oleh Nahorniak
 */
@WebServlet(name = "changeFullName", value = "/changeFullName")
public class ChangeFullNameServlet extends HttpServlet {

    /**
     * doGet method - sendRedirect to error-page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
        String fullName = request.getParameter("fullName");

        if(notNull(fullName)){
            try (Connection connection = connectionPool.getConnection()){
                User user = userDAO.getUser(id,connection);
                user.setFullName(fullName);
                userDAO.updateUser(user,connection);
                response.sendRedirect("profile");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
        }
        return true;
    }
}
