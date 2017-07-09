package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Marinus Toman<br>
 *  Date:  04-Jul-2017
 */
public class QueryD {
    private static PreparedStatement stmt = null;
    /** 
     * Method to read in coordinates from database
     * 
     * @param table Name of table to query
     * @return ResultSet with all table entries
     */
    public static ResultSet getCoordinates(String table){
        // SQL query
        String sql = "SELECT x_point, y_point FROM ratchet." + table;
        ResultSet results = null;
        try{
            // get statement object and execute query
            stmt = DBConn.pStatement(sql);
            results = stmt.executeQuery();   
        }catch(SQLException e){
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
     */
    public static void setCoordinates(String table, double x, double y){
        // SQL query
        String sql = "INSERT INTO ratchet." + table + " (x_point, y_point) VALUES (?, ?)";
        try{
            // get statement object and set parameters
            stmt = DBConn.pStatement(sql);
            stmt.setDouble(1, x);
            stmt.setDouble(2, y);
            // execute query
            stmt.executeUpdate();
            //System.out.println("Write to database successful!!");
        }catch(SQLException e){
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());
        }finally{
            // close db connection
            DBConn.finish();
        }
    }
    
    public static double getAverageError(){
        // SQL query
        String sql = "SELECT avg(difference) FROM ratchet.deca_coordinates";
        // variables
        double average = 0;
        ResultSet results;
        try{
            // get statement object and execute query
            stmt = DBConnection.pStatement(sql);
            results = stmt.executeQuery();
            if(results != null && results.next()){
                average = results.getDouble(1);
            }
        }catch(SQLException e){
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());            
        }
        return average;
    }
}
