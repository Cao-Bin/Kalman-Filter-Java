package Kalman;

import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * @author Marinus Toman
 *  Date:  28-Jun-2017
 */
public class KalmanFilterPosition {
    // Position measurement noise (in meters) => 0.5m
    private final double MEAS_NOISE = 0.5d;
    // Process noise (in meters) => 0.00001m
    private final double PROC_NOISE = 1e-5d;
    // Discrete time interval between steps (500 milliseconds)
    private final double DTIME = 0.5d;
    
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
}