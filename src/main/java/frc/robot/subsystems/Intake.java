package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Logger;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {

    // Intake motor state
    public enum IntakeMotorState {
        ON,
        OFF,
        REVERSED
    }

    // Talon SRX Motor
    private TalonSRX m_intakeMotor = new TalonSRX(Constants.Intake.IntakeID);

    // Current intake mode
    private IntakeMotorState m_currentMode = IntakeMotorState.OFF;

    public Intake(RobotContainer rc) {
        this.setMotorState(IntakeMotorState.OFF);
    }

     /**
     * set the desired intake state
     * @param state wanted intake state
     */
    public void setMotorState(IntakeMotorState state) {
        // set the current state
        this.m_currentMode = state;
        Logger.getInstance().addInfo("Intake", "Motor State Changed To " + state);
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeMotor.set(ControlMode.PercentOutput, Constants.Intake.InSpeed);
                break;
            case OFF:
                // Off
                this.m_intakeMotor.set(ControlMode.PercentOutput, 0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeMotor.set(ControlMode.PercentOutput, Constants.Intake.OutSpeed);
                break;
        }
    }

    /**
     * get the current intake state
     * @return intake state
     */
    public IntakeMotorState getMotorState() {
        // return the current motor state
        return this.m_currentMode;
    }
}
