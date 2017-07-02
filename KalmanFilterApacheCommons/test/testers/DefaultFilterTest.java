package testers;

import connect.DBConnection;
import connect.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import kalman.KalmanFilterDefault;

/**
 * @author Marinus Toman Date: 29-Jun-2017
 */
public class DefaultFilterTest {

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // table names
        String origin = "deca_coordinates";
        String filtered = "deca_coordinates_filtered";
        // Kalman Filters
        KalmanFilterDefault xfilter;
        KalmanFilterDefault yfilter;
        // Error bounds
        double measError = 0.1d;
        double processError = 0.5d;
        // default kalman xfilter
        xfilter = new KalmanFilterDefault(measError, processError);
        yfilter = new KalmanFilterDefault(measError, processError);
        // 2d arrays for x and y coordinates
        double[][] x_points = new double[101][1];  // data sample
        double[][] y_points = new double[101][1];  // data sample

        // get data from db
        ResultSet rs = Query.getCoordinates(origin);
        try {
            for (int i = 0; i < x_points.length && rs.next(); i++) {
                // store results
                x_points[i][0] = rs.getDouble(1);
                y_points[i][0] = rs.getDouble(2);
                //use filter and write results to db
                x_points[i] = xfilter.estimatePosition(x_points[i]);
                y_points[i] = yfilter.estimatePosition(y_points[i]);
                Query.setCoordinates(filtered, x_points[i][0], y_points[i][0]);
            }
        } catch (SQLException e) {
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());
        } finally {
            DBConnection.finish();
        }

//        // display arrays to ensure data is stored properly
//        for(int i = 0; i < x_points.length; i++){
//            System.out.println("X = " + x_points[i][0] + ", Y = " + y_points[i][0]);
//        }
//
//        //use xfilter and write results to db
//        for(int i = 0; i < x_points.length; i++){
//            x_points[i] = xfilter.estimatePosition(x_points[i]);
//            y_points[i] = yfilter.estimatePosition(y_points[i]);
//            Query.setCoordinates(filtered, x_points[i][0], y_points[i][0]);
//        }
    }

}
