package testers;

import connect.Query;
import kalman.KalmanFilterPosition;

/**
 * @author Marinus Toman Date: 06-Jul-2017
 */
public class PositionFilterTest {
    // Number of coordinates to filter
    private final static int POINTS = 101;
    // Table names
    //private static final String ORIGIN = "deca_test";
    private static final String FILTERED = "deca_coordinates_filtered";
    
    // Error bounds
    private final static double MEAS_NOISE = 1000d;
    private final static double PROC_NOISE = 1d;
    private final static double X_ERROR = 0.025d;
    private final static double Y_ERROR = -0.053d;
    
    // Discrete TIME
    private static final double TIME = 0.00001d;
    
    // get data
    private static double[][] filtered_coord = Query.getEstCoordinates();
    private static double[][] est_coord = Query.getEstCoordinates();
    private static double[][] fixed_coord = Query.getFixedCoordinates();
    
    // Initial system state
    private static double x = est_coord[0][0];
    private static double y = est_coord[0][1];
    
    // coordinate kalman filter
    private static KalmanFilterPosition filter = new KalmanFilterPosition(PositionFilterTest.MEAS_NOISE, PositionFilterTest.TIME, x, y);
    
    /**
     * Method to find difference between points
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     * @return Distance between points
     */
    public static double getDifference(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
    }
    
    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        double distance, xError, yError;
        // iterate over whole array
        for (int i = 1; i < POINTS; i++) {
            // use filter
            filtered_coord[i] = filter.estimatePosition(est_coord[i], X_ERROR, Y_ERROR);
            // get difference
            distance = getDifference(filtered_coord[i][0], filtered_coord[i][1], fixed_coord[i][0], fixed_coord[i][1]);
            // write results to db
            Query.setCoordinates(FILTERED, est_coord[i][0], est_coord[i][1], filtered_coord[i][0], filtered_coord[i][1], distance);
        }

    }
}
