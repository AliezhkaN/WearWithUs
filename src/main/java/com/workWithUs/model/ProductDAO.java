package com.workWithUs.model;

import com.workWithUs.model.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.workWithUs.model.constants.Entity.*;

public class ProductDAO {

    private static final String GET_ALL = "SELECT p.id, src, name, g.gender, t.type, price " +
                                          "FROM product p " +
                                          "join genders g on p.gender_id = g.id " +
                                          "join types t on p.type_id = t.id";

    private static final String GET_ALL_GIRLS = "SELECT p.id, src, name, g.gender, t.type, price " +
                                          "FROM product p " +
                                          "join genders g on p.gender_id = g.id " +
                                          "join types t on p.type_id = t.id " +
                                          "where gender = 'FEMALE'";

    private static final String GET_ALL_BOYS = "SELECT p.id, src, name, g.gender, t.type, price " +
            "FROM product p " +
            "join genders g on p.gender_id = g.id " +
            "join types t on p.type_id = t.id " +
            "where gender = 'MALE'";

    private static ProductDAO productDAO;
    public static synchronized ProductDAO getInstance() {
        if (productDAO == null) {
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    private ProductDAO() {
    }

    public List<Product> getAll(Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL);
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()) {
                Product product = productMapper.map(rs);
                products.add(product);
            }
            ConnectionPool.close(rs);
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
        return products;
    }

    public List<Product> getAllGirls(Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_GIRLS);
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()) {
                Product product = productMapper.map(rs);
                products.add(product);
            }
            ConnectionPool.close(rs);
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
        return products;
    }

    public List<Product> getAllBoys(Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL_BOYS);
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()) {
                Product product = productMapper.map(rs);
                products.add(product);
            }
            ConnectionPool.close(rs);
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
        return products;
    }

    private static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product map(ResultSet rs) {
            Product.Builder builder = new Product.Builder();
            Product product = null;
            try {
                product = builder.withId(rs.getInt(ENTITY__ID))
                        .withSrc(rs.getString(PRODUCT__SRC))
                        .withName(rs.getString(PRODUCT__NAME))
                        .withGender(rs.getString(PRODUCT__GENDER))
                        .withType(rs.getString(PRODUCT__TYPE))
                        .withPrice(rs.getInt(PRODUCT__PRICE))
                        .build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return product;
        }
    }

}
