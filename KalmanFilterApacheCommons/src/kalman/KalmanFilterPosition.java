package kalman;

import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * @author Marinus Toman Date: 28-Jun-2017
 */
public class KalmanFilterPosition {

    // Position measurement noise (in meters) => 0.5m
    private final double MEAS_NOISE = 0.5d;
    // Process noise (in meters) => 0.00001m
    private final double PROC_NOISE = 1e-5d;
    // Discrete time interval between steps (500 milliseconds)
    private final double dt = 0.5d;

    // A - state transition matrix
    private RealMatrix A;
    // B - control input matrix
    private RealMatrix B;
    // H - measurement matrix
    private RealMatrix H;
    // Q - process noise covariance matrix (process error)
    private RealMatrix Q;
    // R - measurement noise covariance matrix (measurement error)
    private RealMatrix R;
    // P - error covariance matrix
    private RealMatrix P;
    // x - state
    private RealVector x;

    // Kalman Filter
    private KalmanFilter filter;

    public KalmanFilterPosition(int X, int Y) {
        
        // A = 
        A = new Array2DRowRealMatrix(new double[][]{
            {1d, dt},
            {0d, 1d}
        });
        // B = 
        B = new Array2DRowRealMatrix(new double[][]{
            {Math.pow(dt, 2d) / 2d},
            {dt}
        });
        //only observe first 2 values - the position coordinates
        H = new Array2DRowRealMatrix(new double[][]{
            {1d, 0d, 0d, 0d},
            {0d, 1d, 0d, 0d},
        });
        Q = new Array2DRowRealMatrix(new double[][]{
            {Math.pow(dt, 4d) / 4d, 0d, Math.pow(dt, 3d) / 2d, 0d},
            {0d, Math.pow(dt, 4d) / 4d, 0d, Math.pow(dt, 3d) / 2d},
            {Math.pow(dt, 3d) / 2d, 0d, Math.pow(dt, 2d), 0d},
            {0d, Math.pow(dt, 3d) / 2d, 0d, Math.pow(dt, 2d)}
        });
    }
}
