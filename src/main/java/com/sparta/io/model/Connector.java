package com.sparta.io.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {
    private static Connector instance;
    final String url = "jdbc:sqlite:employee.db";
    private Connection connection = DriverManager.getConnection(url);

    private Connector() throws SQLException {
    }

    public static Connector getInstance() throws SQLException {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
