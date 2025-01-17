package com.api.MvcApi.DBConnection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnectionManager {
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DBconnectionConstant.URL, DBconnectionConstant.USERNAME, DBconnectionConstant.PASSWORD);
            log.info("connection={}", connection);

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
