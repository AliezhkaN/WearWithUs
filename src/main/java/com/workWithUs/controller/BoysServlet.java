package com.workWithUs.controller;

import com.workWithUs.modal.ConnectionPool;
import com.workWithUs.modal.ProductDAO;
import com.workWithUs.modal.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "boys", value = "/boys")
public class BoysServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProductDAO productDAO = ProductDAO.getInstance();
        try (Connection connection = connectionPool.getConnection()){
            List<Product> productList = productDAO.getAllBoys(connection);
            request.setAttribute("products",productList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            request.getRequestDispatcher("WEB-INF/jsp/boys.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
