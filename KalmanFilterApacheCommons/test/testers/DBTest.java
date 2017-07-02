package testers;

import connect.DBConnection;
import connect.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author Marinus Toman Date: 29-Jun-2017
 */
public class DBTest {

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // variables
        String table1 = "deca_coordinates";
        //String table2 = "deca_coordinates_filtered";
        double x = 6;
        double y = 6;
        double multiplier = 5;

        // generate random numbers an write to db
        Random noGen = new Random();
//        for(int i = 0; i < 10; i++){
//            x = noGen.nextGaussian() * 100;
//            y = noGen.nextGaussian() * 100;
//            System.out.println("X=" + x + ", Y=" + y);
//            Query.setCoordinates(table1, x, y);
//        }
        for (int i = 1; i <= 500; i++) {
            if (i % 7 == 0) {
                x += noGen.nextGaussian() * multiplier;
            } else if (i % 5 == 0) {
                y += noGen.nextGaussian() * multiplier;
            } else if (i % 3 == 0) {
                x += noGen.nextGaussian() * multiplier;
                y += noGen.nextGaussian() * multiplier;
            } else {
                x -= noGen.nextGaussian() * multiplier;
                y -= noGen.nextGaussian() * multiplier;
            }
            //System.out.println("X=" + x + ", Y=" + y);
            Query.setCoordinates(table1, x, y);
        }
//
//        // read results from db
//        ResultSet rs = Query.getCoordinates(table1);
//        try {
//            // display results
//            while (rs.next()) {
//                System.out.println("X=" + rs.getDouble(1) + ", Y=" + rs.getDouble(2));
//            }
//        } catch (SQLException e) {
//            // Tell user if unsuccessful
//            System.out.println("Please check SQL syntax and see below for error");
//            System.out.println(e.getMessage());
//        } finally {
//            DBConnection.finish();
//        }
    }

}
