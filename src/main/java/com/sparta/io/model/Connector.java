package com.sparta.io.model;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {
    private static Logger logger = Logger.getLogger("IO Application");
    private static Connector instance;
    final String url = "jdbc:sqlite:employee.db";
    private Connection connection = DriverManager.getConnection(url);

    private Connector() throws SQLException {
    }

    public static Connector getInstance() throws SQLException {
        logger.info("singleton Connector has been accessed.");
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
