package testers;

import connect.DBConnection;
import connect.Query;
import java.sql.ResultSet;
import java.sql.SQLException;

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
//        double x;
//        double y;
        

//        // generate random numbers an write to db
//        Random noGen = new Random();
//        for(int i = 0; i < 10; i++){
//            x = noGen.nextGaussian() * 100;
//            y = noGen.nextGaussian() * 100;
//            System.out.println("X=" + x + ", Y=" + y);
//            Query.setCoordinates(table1, x, y);
//        }
        for (int i = 1; i <= 9000; i++) {
//            if (i % 10 == 0) {
//                x = 5.5;
//                y = 4.5;
//            } else if(i % 5 == 0){
//                x = 6.5;
//                y = 3;
//            }else {
//            x = 6d;
//            y = 4d;
//            }
            //System.out.println("X=" + x + ", Y=" + y);
            Query.setCoordinates(table1, 6, 4);
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
