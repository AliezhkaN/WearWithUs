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
 * ChangeEmailServlet -> used for changing email in personal profile
 *
 * @author Oleh Nahorniak
 */
@WebServlet(name = "changeEmail", value = "/changeEmail")
public class ChangeEmailServlet extends HttpServlet {
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


    /**
     * doPost method collects all info<br>
     * •new Email<br>
     * •confirmed Email<br>
     * •current password<br>
     * if all recent data are correct change user's email
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();

        int id = (int) session.getAttribute("userId");

        String newEmail = request.getParameter("newEmail");
        String confirmNewEmail = request.getParameter("confirmNewEmail");
        String confirmPassword = request.getParameter("confirmPassword");

        try (Connection connection = connectionPool.getConnection()) {
            if (notNull(newEmail, confirmNewEmail, confirmPassword)) {
                String encryptedPassword = Encryption.md5(confirmPassword);
                User user = userDAO.getUser(id,connection);
                if (!newEmail.equals(confirmNewEmail)) {
                    session.setAttribute("error", "Please Confirm your email");
                    response.sendRedirect("profile");
                } else {
                    if (!encryptedPassword.equals(user.getPassword())) {
                        session.setAttribute("error", "Password is not correct");
                        response.sendRedirect("profile");
                    } else {
                            user.setEmail(newEmail);
                            userDAO.updateUser(user, connection);
                            session.invalidate();
                            session = request.getSession();
                            session.setAttribute("message", "Email successfully changed");
                            response.sendRedirect("/application");
                    }
                }
            }
        } catch (SQLException e) {
        String message = e.getMessage();
        if (message.contains("email")) {
            session.setAttribute("error", "Користувач з такою Електронною поштою вже існує");
            response.sendRedirect("profile");
        }
        e.printStackTrace();
    }
    }

    /**
     * notNull method -> checks request parameters on null value
     * @param params
     * @return
     */
    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
            }
        return true;
    }

}
