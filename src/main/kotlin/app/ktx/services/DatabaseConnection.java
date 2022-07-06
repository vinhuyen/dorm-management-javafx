package app.ktx.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection
{
    private final static String DB_URL = "jdbc:mysql://localhost:3306/ktx_db";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "";

    public final static Connection connection = getConnection(DB_URL, USER_NAME, PASSWORD);


    public static Connection getConnection
    (
        String dbURL,
        String userName,
        String password
    )
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        }
        catch (Exception ex)
        {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
