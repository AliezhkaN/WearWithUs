package com.workWithUs.controller.servlets;

import com.workWithUs.model.ConnectionPool;
import com.workWithUs.model.ProductDAO;
import com.workWithUs.model.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "deleteProduct", value = "/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProductDAO productDAO = ProductDAO.getInstance();
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String url = request.getHeader("referer");
        List<Product> list = (List<Product>) session.getAttribute("productS");
        if(id != null && list != null){
            try (Connection connection = connectionPool.getConnection()){
                int productId = Integer.parseInt(id);
                Product product = productDAO.getById(productId,connection);
                list.remove(product);
                session.setAttribute("productS",list);
                session.setAttribute("open",true);
                response.sendRedirect(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
