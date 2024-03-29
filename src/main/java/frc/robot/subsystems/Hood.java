package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.MathUtil;
import frc.lib.ShooterUtil;
import frc.robot.Constants;
import frc.robot.Robot;

/**
 * Hood subsystem.
 */
public class Hood extends PIDSubsystem {
    // Hood motor
    public WPI_TalonSRX m_hoodMotor = new WPI_TalonSRX(Constants.Hood.kMotorID);

    // Current setpoint
    private int m_currentSetpoint;

    // Homing
    private boolean m_homing = false;

    /**
     * TurnTable constructor.
     */
    public Hood() {
        // Set our PID values
        super(new PIDController(Constants.Hood.kP, Constants.Hood.kI, Constants.Hood.kD));

        // Set the tolerance
        this.getController().setTolerance(Constants.Hood.kTolerance);

        // Encoder
        this.m_hoodMotor.setSelectedSensorPosition(0);

        // BRAKE
        this.m_hoodMotor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Turn hood to angle.

     * @param angle angle to turn to (in degrees)
     */
    public void turnToAngle(double angle) {
        angle -= Constants.Hood.kMinimumAngle;
        angle = angle == 0 ? 5.0 : angle;
        SmartDashboard.putNumber("hood_angle", angle);

        this.m_currentSetpoint = ((int) (((910) / (Constants.Hood.kMaximumAngle - Constants.Hood.kMinimumAngle)) * angle));

        // Setpoint
        this.setSetpoint(this.m_currentSetpoint);
        this.getController().setSetpoint(this.m_currentSetpoint);

        Robot.logger.addInfo("hood", "Turning to angle: " + angle);
    }

    /**
     * Hood is at target.

     * @return whether we are at target
     */
    public boolean atTarget() {
        return this.m_controller.atSetpoint();
    }

    /**
     * Home the hood. (Ram into the hard stop)
     */
    public void home() {
        Robot.logger.addInfo("hood", "Starting homing..");
        this.turnToAngle(Constants.Hood.kMinimumAngle);
        this.m_homing = true;
    }

    /**
     * Check if the intake homing.

     * @return Homing boolean
     */
    public boolean isHoming() {
        return this.m_homing;
    }

    /**
     * Move the hood to the optimal angle for shooting based on velocity.

     * @return False if the angle is out of bounds
     */
    public boolean findTargetPosition(double velocity) {
        double angle = ShooterUtil.calculateHoodAngle(velocity, Constants.Vision.kFieldGoalHeightFromGround);
        angle = MathUtil.clamp(angle, Constants.Hood.kMinimumAngle, Constants.Hood.kMaximumAngle);
        if (ShooterUtil.withinBounds(angle)) {
            this.turnToAngle(angle);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Use the output generated by the PID.
     */
    @Override
    public void useOutput(double output, double setpoint) {
        output = MathUtil.clamp(-output, -Constants.Hood.kMaxSpeed, Constants.Hood.kMaxSpeed);
        SmartDashboard.putNumber("hood_setpoint", setpoint);
        SmartDashboard.putNumber("hood_position", this.getMeasurement());
        SmartDashboard.putNumber("hood_output", output);
        if (!this.m_homing) {
            // Output
            this.m_hoodMotor.set(output);
        } else {
            // System.out.println(Robot.PDP.getCurrent(Constants.PdpPorts.kHoodMotor));
            // If current draw is above XX, it's hit the hard stop and zeroed.
            if (Robot.PDP.getCurrent(Constants.PdpPorts.kHoodMotor) > 3.5) {
                this.m_homing = false;
                this.m_hoodMotor.setSelectedSensorPosition(0.0);
                Robot.logger.addInfo("hood", "Homing complete");
            } else {
                this.m_hoodMotor.set(0.25);
            }
        }
    }

    /**
     * Get the encoder position.
     */
    @Override
    public double getMeasurement() {
        // System.out.println(-this.m_hoodMotor.getSelectedSensorPosition());
        return -this.m_hoodMotor.getSelectedSensorPosition();
    }
}