package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Marinus Toman Date: 29-Jun-2017
 */
public class Query {

    private static PreparedStatement stmt = null;

    /**
     * Method to read in coordinates from database
     *
     * @param table Name of table to query
     * @return ResultSet with all table entries
     */
    static ResultSet getCoordinates(String table) {
        // SQL query
        String sql = "SELECT fixed_x, fixed_y, calcu_x, calcu_y FROM filter." 
                + table + " WHERE id < 150";
        ResultSet results = null;
        try {
            // get statement object and execute query
            stmt = DBConnection.pStatement(sql);
            results = stmt.executeQuery();
        } catch (SQLException e) {
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());
        } 
        
        return results;
    }

    /**
     * Method to write out coordinates to database
     *
     * @param table
     * @param x
     * @param y
     * @param fx
     * @param fy
     * @param distance
     * @param xError
     * @param yError
     */
    public static void setCoordinates(String table, double x, double y, double fx, double fy, double distance) {
        // SQL query
        String sql = "INSERT INTO filter." + table + " (calcu_x, calcu_y, filtered_x, filtered_y, difference) VALUES (?, ?, ?, ?, ?)";
        try {
            // get statement object and set parameters
            stmt = DBConnection.pStatement(sql);
            stmt.setDouble(1, x);
            stmt.setDouble(2, y);
            stmt.setDouble(3, fx);
            stmt.setDouble(4, fy);
            stmt.setDouble(5, distance);
            //stmt.setDouble(6, xError);
            //stmt.setDouble(7, yError);
            // execute query
            stmt.executeUpdate();
            //System.out.println("Write to database successful!!");
        } catch (SQLException e) {
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());
        } finally {
            // close db connection
            DBConnection.finish();
        }
    }

    public static double getAverageError(String table) {
        // SQL query
        String sql = "SELECT avg(difference) FROM filter." + table;
        // variables
        double average = 0;
        ResultSet results;
        try {
            // get statement object and execute query
            stmt = DBConnection.pStatement(sql);
            results = stmt.executeQuery();
            if (results != null && results.next()) {
                average = results.getDouble(1);
            }
        } catch (SQLException e) {
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());
        }finally{
            DBConnection.finish();
        }
        return average;
    }

    public static double[][] getEstCoordinates() {
        // variables
        double[][] coordinates;
        int rows = 0;
        
        // get data from db
        ResultSet rs = getCoordinates("deca_test");
        
        // get number of rows returned
        try {
            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }
        } catch (SQLException e) {
            System.out.println("Error....See Below");
            System.out.println(e.getMessage());
        }
        
        // create 2d array to store coordinates
        coordinates = new double[rows][2];
        
        // populate 2d array
        try {
            for (int i = 0; i < rows && rs.next(); i++) {
                coordinates[i][0] = rs.getDouble(3);
                coordinates[i][1] = rs.getDouble(4);
            }
        } catch (SQLException e) {
            System.out.println("Error....See Below");
            System.out.println(e.getMessage());
        }finally{
            DBConnection.finish();
        }
        
        return coordinates;
    }
    
    public static double[][] getFixedCoordinates() {
        // variables
        double[][] coordinates;
        int rows = 0;
        
        // get data from db
        ResultSet rs = getCoordinates("deca_test");
        
        // get number of rows returned
        try {
            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }
        } catch (SQLException e) {
            System.out.println("Error....See Below");
            System.out.println(e.getMessage());
        }
        
        // create 2d array to store coordinates
        coordinates = new double[rows][2];
        
        // populate 2d array
        try {
            for (int i = 0; i < rows && rs.next(); i++) {
                coordinates[i][0] = rs.getDouble(1);
                coordinates[i][1] = rs.getDouble(2);
            }
        } catch (SQLException e) {
            System.out.println("Error....See Below");
            System.out.println(e.getMessage());
        }finally{
            DBConnection.finish();
        }
        
        return coordinates;
    }
}
