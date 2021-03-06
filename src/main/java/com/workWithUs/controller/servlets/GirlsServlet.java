package com.workWithUs.controller.servlets;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.ProductDAO;
import com.workWithUs.model.entity.Product;
import com.workWithUs.model.entity.Type;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "girls", value = "/girls")
public class GirlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProductDAO productDAO = ProductDAO.getInstance();
        String type = request.getParameter("type");
        try (Connection connection = connectionPool.getConnection()){
            List<Product> productList = productDAO.getAllGirls(connection);
            if(type != null){

                if(type.equals("t")){
                    productList = productList.stream()
                            .filter(x-> x.getType().equals(Type.T_SHIRT)).collect(Collectors.toList());
                }else if(type.equals("s")){
                    productList = productList.stream()
                            .filter(x-> x.getType().equals(Type.SWEATSHIRT)).collect(Collectors.toList());
                }
            }
            request.setAttribute("products",productList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("WEB-INF/jsp/girls.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
