package Kalman;

import org.ejml.data.DMatrixRMaj;

/**
 * @author Marinus Toman
 *  Date:  28-Jun-2017
 */
public interface KalmanFilter {
/**
     * Specify the model of the Kalman Filter.
     * This method must be called before any other methods.
     *
     * @param F State transition matrix
     * @param Q Plant noise
     * @param H Measurement projection
     */
    void configure(DMatrixRMaj F, DMatrixRMaj Q, DMatrixRMaj H);

    /**
     * The prior system state estimate and covariance.
     *
     * @param x The estimated system state.
     * @param P The covariance of the estimated system state.
     */
    void setState(DMatrixRMaj x, DMatrixRMaj P);

    /**
     * Predict the state of the system, one time step ahead.
     */
    void predict();

    /**
     * Updates the state provided the observation from a sensor.
     *
     * @param z Measurement.
     * @param R Measurement covariance.
     */
    void update(DMatrixRMaj z , DMatrixRMaj R);

    /**
     * Returns the current estimated state of the system.
     *
     * @return The estimated state.
     */
    DMatrixRMaj getState();

    /**
     * Returns the estimated state's covariance matrix.
     *
     * @return The covariance matrix.
     */
    DMatrixRMaj getCovariance();
}
