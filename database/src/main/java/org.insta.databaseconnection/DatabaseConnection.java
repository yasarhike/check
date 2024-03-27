package org.insta.databaseconnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;
    private static final Logger LOGGER =  LogManager.getLogger(DatabaseConnection.class);

    private DatabaseConnection() {
    }

    public static Connection get() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/instagram",
                        "postgres",
                        "Yasar@123");
                LOGGER.debug("Connected successfully");
            } catch (SQLException sqlException) {
                LOGGER.debug("Connection failed");
            }
        }

        return connection;
    }
}
