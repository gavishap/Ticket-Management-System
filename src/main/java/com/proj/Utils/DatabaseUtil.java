package com.proj.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseUtil {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/eventfulDB";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Password123";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

            // Check if database exists
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rs = meta.getCatalogs();
            boolean databaseExists = false;
            while (rs.next()) {
                if ("eventfulDB".equalsIgnoreCase(rs.getString("TABLE_CAT"))) {
                    databaseExists = true;
                    break;
                }
            }
            // If database does not exist, create it
            // If database does not exist, create it
            if (!databaseExists) {

                Statement stmt = connection.createStatement();
                stmt.execute("CREATE DATABASE eventfulDB");
                stmt.close();

                // Run SQL scripts to create tables
                String content = "";
                try {
                    content = new String(Files.readAllBytes(Paths.get("sql/create_tables.sql")),
                            StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Split by semicolon to get each SQL statement
                String[] sqlStatements = content.split(";");

                Statement createTablesStmt = connection.createStatement();
                // Execute each statement
                for (String sqlStatement : sqlStatements) {
                    if (sqlStatement.trim().length() > 0) { // Avoid executing empty statements
                        createTablesStmt.execute(sqlStatement.trim());
                    }
                }
                createTablesStmt.close();
            }

        } catch (SQLException e) {
            System.out.println("Unable to connect to the database.");
            e.printStackTrace();
        }

        return connection;
    }

}