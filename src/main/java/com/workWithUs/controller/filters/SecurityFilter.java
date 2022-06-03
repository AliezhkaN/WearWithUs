package com.workWithUs.controller.filters;

import com.workWithUs.model.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Security filter.
 *
 * @author Oleh Nahorniak
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {



    private static Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private static List<String> commons = new ArrayList<String>();
    private static List<String> outOfControl = new ArrayList<String>();


    /**
     * Security filter initialization to get rights for all user roles from web.xml to access pages.
     */
    public void init(FilterConfig config) throws ServletException {


        accessMap.put(Role.ADMIN, asList(config.getInitParameter("admin")));
        accessMap.put(Role.CUSTOMER, asList(config.getInitParameter("client")));

        commons = asList(config.getInitParameter("common"));

        outOfControl = asList(config.getInitParameter("out-of-control"));

    }

    /**
     * Destroy method.
     */

    public void destroy() {
        // do nothing
    }

    /**
     * doFilter method checks request and user role to get it.
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(404);
        }
    }

    /**
     * accessAllowed method answer a question is it possible to get specific page
     *
     * @param request
     * @return access to page true / false
     */
    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String pageName = httpRequest.getHttpServletMapping().getServletName();
        if (pageName == null || pageName.isEmpty())
            return false;

        if (outOfControl.contains(pageName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        Role userRole = (Role) session.getAttribute("role");

        if (userRole == null)
            return false;



        return accessMap.get(userRole).contains(pageName)
                || commons.contains(pageName);
    }

    /**
     * asList method split param string from web.xml into a list
     *
     * @param str
     * @return
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
