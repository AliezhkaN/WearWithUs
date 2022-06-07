package com.workWithUs.controller.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "changeLocale", value = "/changeLocale")
public class ChangeLocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("lang");
        String url = request.getHeader("referer");
        if (language != null) {
            Cookie lang = getLangCookie(request);
            lang.setValue(language);
            response.addCookie(lang);
            request.getSession().setAttribute("lang", language);
        }
        response.sendRedirect(url);
    }

    private Cookie getLangCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("lang")) {
                    return cookie;
                }
            }
        }

        return null;
    }
}
