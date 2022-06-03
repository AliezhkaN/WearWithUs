package com.workWithUs.controller.servlets;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.UserDAO;
import com.workWithUs.model.entity.User;
import com.workWithUs.util.Encryption;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "signUp", value = "/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String fullName =request.getParameter("fullName");
        String phoneNumber =request.getParameter("phone");
        String password =request.getParameter("password");
        String confirm =request.getParameter("confirm");

        if(notNull(email,fullName,phoneNumber,password,confirm) && confirm.equals(password)){
            String encryptedPassword = Encryption.md5(password);
            try (Connection connection = connectionPool.getConnection()){
            User newUser = new User.Builder()
                    .withEmail(email)
                    .withFullName(fullName)
                    .withPhoneNumber(phoneNumber)
                    .withPassword(encryptedPassword)
                    .build();

            int id = userDAO.insertUser(newUser,connection);
                newUser = userDAO.getUser(id,connection);
                session.setAttribute("userId",newUser.getId());
                session.setAttribute("role",newUser.getRole());
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
