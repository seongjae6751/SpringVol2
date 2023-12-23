package com.api.MvcApi.repository;

import com.api.MvcApi.DBConnection.DBConnectionManager;
import com.api.MvcApi.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
@Repository
public class UserRepository {
    // 유저 전체 조회
    public ArrayList<User> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from user";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();

            ArrayList<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            log.error("selectUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, rs);//사용한 리소스 반환
        }
    }
    // 특정 유저 조회
    public User findById(String userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from user where id = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, userId);

            rs = statement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            log.error("selectUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, rs);//사용한 리소스 반환
        }
    }
    // 유저 등록
    public User createUser(User newUser) throws SQLException{
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "insert into user(id, username, password) values(?, ?, ?)";//DB에 넘길 SQL 작성

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, newUser.getId());
            statement.setString(2, newUser.getUsername());
            statement.setString(3, newUser.getPassword());

            statement.executeUpdate();
            return newUser;
        } catch (SQLException e) {
            log.error("createUser error = {}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);
        }
    }

    // 유저 수정
    public void updateUsername(String id, String newUsername) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update user set username = ? where id = ?";

        try {
            connection = DBConnectionManager.getConnection();//DriverManger 통해서 DB커넥션 생성
            statement = connection.prepareStatement(sql);//SQL실행 하기위한 객체 PrepareStatement 생성

            statement.setString(1, newUsername);//DB컬럼과 자바 오브젝트 필드 바인딩
            statement.setString(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("updateUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);//사용한 리소스 반환
        }
    }
    // 유저 삭제
    public void deleteUser(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from user where id = ?";

        try {
            connection = DBConnectionManager.getConnection();//DriverManger 통해서 DB커넥션 생성
            statement = connection.prepareStatement(sql);//SQL실행 하기위한 객체 PrepareStatement 생성

            statement.setString(1, id);//DB컬럼과 자바 오브젝트 필드 바인딩

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("deleteUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);//사용한 리소스 반환
        }
    }

    private void closeResource(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        //반환할 때는 반드시 역순으로 반환해야 함.
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
    }
}
