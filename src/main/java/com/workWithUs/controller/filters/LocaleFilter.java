package com.workWithUs.controller.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Locale filter.
 *
 * @author Oleh Nahorniak
 */
@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {


    /**
     * Destroy method.
     */

    public void destroy() {
        // do nothing
    }


    /**
     * doFilter method , takes locale from cookies and set it to session
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.isNew()) {
            Cookie lang = null;
            Cookie[] cookies = req.getCookies();

            if (cookies != null)
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("lang")) lang = cookie;
                }

            if (lang == null) {
                lang = new Cookie("lang", "uk");
                lang.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(lang);
            }

            session.setAttribute("lang", lang.getValue());
        } else if (session.getAttribute("lang") == null) session.setAttribute("lang", "en");

        chain.doFilter(request, response);
    }
}
