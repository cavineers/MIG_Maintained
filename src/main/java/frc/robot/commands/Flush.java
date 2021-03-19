package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.IntakeMotorState;
import frc.robot.subsystems.Transportation.TransportMotorState;

/**
 * Flush the robot class.
 */
public class Flush extends CommandBase {

    // Constructor
    public Flush() {
        this.addRequirements(Robot.intake, Robot.transportation);
    }

    // Set Motor State to OFF / REVERSED
    @Override
    public void initialize() {
        Robot.logger.addInfo("Flush", "Toggling Flush Systems");

        // Reverse systems
        if(Robot.transportation.getConveyorMotorState() == TransportMotorState.OFF && Robot.transportation.getFeederMotorState() == TransportMotorState.OFF
            && Robot.intake.getMotorState() == IntakeMotorState.OFF) {
            // Reverse Transportation / Intake
            Robot.transportation.setConveyorMotorState(TransportMotorState.REVERSED);
            Robot.transportation.setFeederMotorState(TransportMotorState.REVERSED);
            Robot.intake.setMotorState(IntakeMotorState.REVERSED);
        } else {
            // Turn off all systems
            Robot.transportation.setConveyorMotorState(TransportMotorState.OFF);
            Robot.transportation.setFeederMotorState(TransportMotorState.OFF);
            Robot.intake.setMotorState(IntakeMotorState.OFF);
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
