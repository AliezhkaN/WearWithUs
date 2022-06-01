package com.workWithUs.controller.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Encoding filter.
 *
 * @author Oleh Nahorniak
 */

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private String encoding;


    /**
     * Destroy method.
     */

    public void destroy() {
        //-----------------
    }

    /**
     * doFilter method, set request encoding -> in my case 'UTF-8'
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;



        String requestEncoding = request.getCharacterEncoding();

        if (requestEncoding == null) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    /**
     * Encoding filter initialization to get encoding from web.xml.
     */
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("encoding");
    }
}
