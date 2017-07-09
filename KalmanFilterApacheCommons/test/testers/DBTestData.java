package testers;

import connect.Query;
import java.util.Random;

/**
 * @author Marinus Toman
 *  Date:  04-Jul-2017
 */
public class DBTestData {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // variables
        String tableName = "deca_coordinates";
        //String tableName1 = "wifi_coordinates_estimated";
        //String tableName2 = "wifi_coordinates_filtered";
        double x = 4.7;
        double y = 0.65;
        //double multiplier = 2;
        // random number generator
        Random noGen = new Random();
        for (int i = 1; i <= 500; i++) {
//            if (i % 7 == 0) {
//                x += noGen.nextGaussian() * multiplier;
//            } else if (i % 5 == 0) {
//                y += noGen.nextGaussian() * multiplier;
//            } else if (i % 3 == 0) {
//                x += noGen.nextGaussian() * multiplier;
//                y += noGen.nextGaussian() * multiplier;
//            } else {
//                x -= noGen.nextGaussian() * multiplier;
//                y -= noGen.nextGaussian() * multiplier;
//            }
            //System.out.println("X=" + x + ", Y=" + y);
            Query.setCoordinates(tableName, x, y);
            //QueryD.setCoordinates(tableName1, x, y);
            //QueryD.setCoordinates(tableName2, x, y);
        }
    }

}
