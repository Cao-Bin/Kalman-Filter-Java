package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Marinus Toman<br>
 * Date: 30-Jun-2017
 */
public class DBConn {

    /**
     * Instance Fields
     */
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://52.214.73.212:3306/ratchet?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "denis";
    private static final String DB_PASSWORD = "P*K43$wRv8";
    private static Connection con = null;

    /**
     * Method to make a connection to the database
     */
    private static Connection getConnection() {

        try {
            // Register SQL driver
            Class.forName(DB_DRIVER);

            // Open connection
            con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

            // Tell user if successful
            System.out.println("Connection Open");

        } catch (ClassNotFoundException | SQLException e) {
            // Tell user if unsuccessful
            System.out.println("Database Connection Unsuccessful... See Below");
            System.out.println(e.getMessage());
        }

        return con;
    }

    /**
     * Method to take a string and make it a SQL PreparedStatement
     *
     * @param statementIn String query
     * @return PreparedStatement statement
     */
    static PreparedStatement pStatement(String statementIn) {
        PreparedStatement statement = null;
        try {
            // open connection and get PreparedStatement
            statement = getConnection().prepareStatement(statementIn);
        } catch (SQLException e) {
            // tell user if unsuccessful
            System.out.println("Please check SQL syntax");
            System.out.println(e.getMessage());
        }
        return statement;
    }

    /**
     * Method to close the connection
     *
     */
    public static void finish() {
        // check if connection has been initialized
        if (con != null) {
            try {
                // try to close connection
                con.close();
                System.out.println("Connection Closed");
            } catch (SQLException e) {
                // tell user if unsuccessful
                System.out.println("Connection can't close!");
                System.out.println(e.getMessage());
            }

        }

    }
}
