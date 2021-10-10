package com.sparta.io.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLConnection {
    private static SQLConnection instance;
    final String url = "jdbc:sqlite:employee.db";
    private Connection connection = DriverManager.getConnection(url);

    private SQLConnection() throws SQLException {
    }

    public static SQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new SQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
