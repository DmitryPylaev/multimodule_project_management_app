package com.digdes.java2023.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
    private static final CustomProperties APPLICATION_PROPERTIES = new CustomProperties("application");
    private static final String dbUser = APPLICATION_PROPERTIES.getPropertyContent("username");
    private static final String dbPass = APPLICATION_PROPERTIES.getPropertyContent("password");
    private static final String driver = APPLICATION_PROPERTIES.getPropertyContent("driver");
    private static final String url = APPLICATION_PROPERTIES.getPropertyContent("url");

    public static Connection getDbConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, dbUser, dbPass);
    }
}
