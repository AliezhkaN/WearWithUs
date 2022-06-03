package com.workWithUs.model;

import com.workWithUs.model.entity.User;

import java.sql.*;

import static com.workWithUs.model.constants.Entity.*;

public class UserDAO {

    private static String GET_USER = "select u.id, email,password, full_name,r.role,is_blocked,phone,avatar from users u join roles r on r.id =u.role_id where email=? and password = ?";
    private static String GET_USER_BY_ID = "select u.id, email,password, full_name,r.role,is_blocked,phone,avatar from users u join roles r on r.id =u.role_id where u.id=?";
    private static String INSERT_USER = "INSERT INTO users(email, password, full_name, phone) " +
            "VALUES (?,?,?,?)";
    private static String UPDATE_USER = "UPDATE users " +
            "SET email=?, password=?, full_name=?, phone=? ,is_blocked=?,avatar=?" +
            "WHERE id=?";

    private static UserDAO userDAO;
    public static synchronized UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    private UserDAO() {
    }

    public int insertUser(User user, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFullName());
            ps.setString(4,user.getPhoneNumber());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.commit(connection);
        }
        return 0;
    }

    public User getUser(String email, String password, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.map(rs);
            }
            ConnectionPool.close(rs);
            return user;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public User getUser(int id, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.map(rs);
            }
            ConnectionPool.close(rs);
            return user;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public void updateUser(User user, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)){
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFullName());
            ps.setString(4,user.getPhoneNumber());
            ps.setBoolean(5,user.getBlocked());
            ps.setString(6,user.getAvatar());
            ps.setInt(7,user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.commit(connection);
        }
    }



    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User map(ResultSet rs) {
            User.Builder builder = new User.Builder();
            User person = null;
            try {
                person = builder.withId(rs.getInt(ENTITY__ID))
                        .withFullName(rs.getString(USER__FULL_NAME))
                        .withPassword(rs.getString(USER__PASSWORD))
                        .withRole(rs.getString(USER__ROLE))
                        .withEmail(rs.getString(USER__EMAIL))
                        .withPhoneNumber(rs.getString(USER__PHONE_NUMBER))
                        .withStatus(rs.getBoolean(USER__IS__BLOCKED))
                        .withAvatar(rs.getString(USER__AVATAR))
                        .build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return person;
        }
    }
}
