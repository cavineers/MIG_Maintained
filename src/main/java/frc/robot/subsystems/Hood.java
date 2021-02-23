package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Hood extends PIDSubsystem {
    // Hood motor
    public WPI_TalonSRX m_hoodMotor = new WPI_TalonSRX(Constants.Hood.kMotorID);

    // Current setpoint
    private int m_currentSetpoint;

    /**
     * TurnTable constructor
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
     * turnToAngle
     * @param angle angle to turn to (in degrees)
     */
    public void turnToAngle(double angle) {
        this.m_currentSetpoint = ((int)((4096/360)*angle));

        // Setpoint
        this.setSetpoint(this.m_currentSetpoint);
        this.getController().setSetpoint(this.m_currentSetpoint);
    }

    /**
     * atTarget
     * @return whether we are at target
     */
    public boolean atTarget() {
        return (this.m_currentSetpoint-Constants.Hood.kTolerance<getMeasurement() && this.m_currentSetpoint+Constants.Hood.kTolerance > getMeasurement());
    }

    /**
     * Use the output generated by the PID
     */
    @Override
    public void useOutput(double output, double setpoint) {
        // Output
        this.m_hoodMotor.set(MathUtil.clamp(-output,-Constants.Hood.kMaxSpeed,Constants.Hood.kMaxSpeed));
    }

    /**
     * Get the encoder position
     */
    @Override
    public double getMeasurement() {
        return -this.m_hoodMotor.getSelectedSensorPosition();
    }
}