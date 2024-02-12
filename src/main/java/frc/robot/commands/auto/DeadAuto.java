package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

/**
 * Dead autonomous command.
 */
public class DeadAuto extends Command {
    public DeadAuto() {}

    @Override
    public void initialize() {
        Robot.logger.addWarn("DeadAuto", "Dead autonomous command initialized");
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
