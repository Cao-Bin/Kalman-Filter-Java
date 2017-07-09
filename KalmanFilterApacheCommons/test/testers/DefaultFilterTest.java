package testers;

import connect.DBConnection;
import connect.QueryD;
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
        // Error bounds for X
        double measErrorX = 0.3d;
        double processErrorX = 0.0001d;
        double stateX = 6d;
        // Error bounds for Y
        double measErrorY = 0.4d;
        double processErrorY = 0.0001d;
        double stateY = 1d;
        // default kalman xfilter
        xfilter = new KalmanFilterDefault(measErrorX, processErrorX, stateX);
        yfilter = new KalmanFilterDefault(measErrorY, processErrorY, stateY);
        // 2d arrays for x and y coordinates
        double[][] x_points = new double[150][1];  // data sample
        double[][] y_points = new double[150][1];  // data sample

        // get data from db
        ResultSet rs = QueryD.getCoordinates(origin);
        try {
            // iterate over whole array
            for (int i = 0; i < x_points.length && rs.next(); i++) {
                // store results
                x_points[i][0] = rs.getDouble(1);
                y_points[i][0] = rs.getDouble(2);
                // use filter and write results to db
                x_points[i] = xfilter.estimatePosition(x_points[i]);
                y_points[i] = yfilter.estimatePosition(y_points[i]);
                QueryD.setCoordinates(filtered, x_points[i][0], y_points[i][0]);
            }
        } catch (SQLException e) {
            // Tell user if unsuccessful
            System.out.println("Please check SQL syntax and see below for error");
            System.out.println(e.getMessage());
        } finally {
            DBConnection.finish();
        }
    }
}
