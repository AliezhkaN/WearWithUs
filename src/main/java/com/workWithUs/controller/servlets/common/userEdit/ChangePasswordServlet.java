package com.workWithUs.controller.servlets.common.userEdit;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.UserDAO;
import com.workWithUs.model.entity.User;
import com.workWithUs.util.Encryption;

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
 * ChangePasswordServlet -> used for changing password in personal profile
 *
 * @author Oleh Nahorniak
 */
@WebServlet(name = "changePassword", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {

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
        UserDAO userDao = UserDAO.getInstance();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");


        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        try (Connection connection = connectionPool.getConnection()){
            if(notNull(currentPassword,newPassword,confirmNewPassword)){
                User user = userDao.getUser(id,connection);

                if(!Encryption.md5(currentPassword).equals(user.getPassword())){
                    session.setAttribute("error", "PASSWORD_IS_NOT_CORRECT");
                    response.sendRedirect("profile");
                }else {
                    if(!newPassword.equals(confirmNewPassword)){
                        session.setAttribute("error", "PLEASE_CONFIRM_PASSWORD");
                        response.sendRedirect("profile");
                    }else {
                        String encryptedPassword = Encryption.md5(newPassword);
                        user.setPassword(encryptedPassword);
                        userDao.updateUser(user,connection);
                        session.invalidate();
                        session = request.getSession();
                        session.setAttribute("message","PASSWORD_SUCCESSFULLY_CHANGED");
                        response.sendRedirect("/application");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
        }
        return true;
    }
}
