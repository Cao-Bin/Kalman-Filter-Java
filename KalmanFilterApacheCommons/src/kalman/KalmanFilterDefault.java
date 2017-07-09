package kalman;

import org.apache.commons.math3.filter.DefaultMeasurementModel;
import org.apache.commons.math3.filter.DefaultProcessModel;
import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * @author Marinus Toman Date: 29-Jun-2017
 */
public class KalmanFilterDefault {

    // Position measurement noise
    private final double MEAS_NOISE;
    // Process noise 
    private final double PROC_NOISE;

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
    // Kalman filter
    private KalmanFilter filter;

    /**
     * Constructs a default (static) Kalman Filter.
     * @param measurementNoise Measurement error
     * @param processNoise Process error
     */
    public KalmanFilterDefault(double measurementNoise, double processNoise, double init) {
        // Set noise constants
        this.MEAS_NOISE = measurementNoise;
        this.PROC_NOISE = processNoise;
        // A = [ 1 ]
        A = new Array2DRowRealMatrix(new double[]{1d});
        // B = null
        B = null;
        // H = [ 1 ]
        H = new Array2DRowRealMatrix(new double[]{1d});
        // x = [ 0 ]
        x = new ArrayRealVector(new double[]{init});
        // Q = [ ? ]
        Q = new Array2DRowRealMatrix(new double[]{this.PROC_NOISE});
        // P = [ 1 ]
        P = new Array2DRowRealMatrix(new double[]{1d});
        //P = null;
        // R = [ ? ]
        R = new Array2DRowRealMatrix(new double[]{this.MEAS_NOISE});

        // Create process model, measurement model and kalman filter
        ProcessModel pm = new DefaultProcessModel(A, B, Q, x, P);
        MeasurementModel mm = new DefaultMeasurementModel(H, R);
        filter = new KalmanFilter(pm, mm);
    }
    
    public double[] estimatePosition(double[] xy){
        filter.predict();
        filter.correct(xy);
        return filter.getStateEstimation();
    }
}
