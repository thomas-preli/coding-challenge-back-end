package com.codingchallengebackend;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class H2DatabaseService {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    static void createWithPreparedStatement() {
        PreparedStatement createPreparedStatement;
        String createQuery = "CREATE TABLE SYMBOL(symbol varchar(10) primary key, name varchar(80))";

        try (Connection connection = getDBConnection()) {
            connection.setAutoCommit(false);

            createPreparedStatement = connection.prepareStatement(createQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();

            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void insertWithPreparedStatement(String symbol, String name) {
        PreparedStatement insertPreparedStatement;
        String insertQuery = "INSERT INTO SYMBOL (symbol, name) VALUES (?,?)";

        try (Connection connection = getDBConnection()) {
            connection.setAutoCommit(false);

            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.setString(1, symbol);
            insertPreparedStatement.setString(2, name);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> selectWithPreparedStatement() {
        PreparedStatement selectPreparedStatement;
        String selectQuery = "SELECT * from SYMBOL";
        ResultSet resultSet;
        Map<String, String> symbolMapping = new HashMap<>();

        try {
            try (Connection connection = getDBConnection()) {
                selectPreparedStatement = connection.prepareStatement(selectQuery);
                resultSet = selectPreparedStatement.executeQuery();

                while (resultSet.next()) {
                    String symbol = resultSet.getString("symbol");
                    String name = resultSet.getString("name");
                    symbolMapping.put(symbol, name);
                }

                resultSet.close();
                selectPreparedStatement.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return symbolMapping;
    }

    private static Connection getDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }
}
