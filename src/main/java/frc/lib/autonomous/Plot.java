package frc.lib.autonomous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

/**
 * Plot for autonomous path that includes robot's position and tolerances.
 */
public class Plot extends Pose2d {
    private double m_transitionTolerance;
    private double m_rotationalTolerance;
    private double m_endingVelocity;

    /**
     * Create Plot.

     * @param x X Position
     * @param y Y Position
     * @param rotation2d Rotation
     * @param transitionTolerance Translation Tolerance
     * @param rotationTolerance Rotation Tolerance
     */
    public Plot(double x, double y, Rotation2d rotation2d, double transitionTolerance, double rotationTolerance, double endingVelocity) {
        super(x, y, rotation2d);
        setTransitionTolerance(transitionTolerance);
        setRotationTolerance(rotationTolerance);
        setEndingVelocity(endingVelocity);
    }

    public void setTransitionTolerance(double tolerance) {
        this.m_transitionTolerance = tolerance;
    }

    public void setRotationTolerance(double tolerance) {
        this.m_rotationalTolerance = tolerance;
    }

    public void setEndingVelocity(double velocity) {
        this.m_endingVelocity = velocity;
    }

    public double getTranslationTolerance() {
        return this.m_transitionTolerance;
    }

    public double getRotationTolerance() {
        return this.m_rotationalTolerance;
    }

    public double getEndingVelocity() {
        return this.m_endingVelocity;
    }
}
