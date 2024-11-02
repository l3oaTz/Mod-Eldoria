package l3oatz.eldoria.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLHandler
{
    private String url;
    private String username;
    private String password;
    private static Connection connection;

    public MySQLHandler()
    {
        this.url = "jdbc:mysql://localhost:3306/eldoria";
        this.username = "root";
        this.password = "";
        connect();
    }

    // 
    private void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SQL
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeQuery();
    }

    public int executeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeUpdate();
    }
    
    public static boolean doesPlayerExist(String uuid)
    {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM character_detail WHERE uuid = ?");
            stmt.setString(1, uuid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 
    }
}
