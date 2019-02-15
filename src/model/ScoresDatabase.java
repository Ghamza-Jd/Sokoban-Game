package model;

import java.sql.*;

public class ScoresDatabase {

    private Connection connection;
    private final String SERVER_NAME = "localhost:3306";
    private final String DATABASE_NAME = "score";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String TABLE_NAME = "score";

    public ScoresDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection =
                    DriverManager.getConnection("jdbc:mysql://" + SERVER_NAME + "/" + DATABASE_NAME,
                            USERNAME,
                            PASSWORD);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void insert(String name, int moves, int level) throws SQLException {
        Statement statement = connection.createStatement();
        String insertScore = "INSERT INTO " + TABLE_NAME + " VALUES('" + name + "', " + moves + ", " + level + ")";
        statement.execute(insertScore);
    }

    public ResultSet getLevelHighScores(int level) throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE level = " + level);
    }

    public void closeDatabase() throws SQLException {
        this.connection.close();
    }
}
