package com.workWithUs.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBConnection class
 *
 *
 */
public class ConnectionPool {
    private static ConnectionPool instance;

    private DataSource ds;

    /**
     * method to get instance of connection pool
     *
     * @return connection pool
     */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Connection pool constructor
     */
    private ConnectionPool() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/WearWithUs");
        } catch (Exception e) {
            throw new IllegalStateException("DBConnection initialization error", e);
        }
    }

    /**
     * method that get connection from connection pool
     *
     * @return Connection from connection pool
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }


    /**
     * Commit all changes on transaction
     *
     * @param con
     */
    static void commit(Connection con) {
        try {
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * rollback all changes on transaction
     *
     * @param con
     */
    static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * close Autocloseable objects
     *
     * @param autoCloseable
     */
    static void close(AutoCloseable autoCloseable) {
        try {
            if (autoCloseable != null) {
                autoCloseable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}