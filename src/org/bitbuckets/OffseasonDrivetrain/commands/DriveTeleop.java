
package org.bitbuckets.OffseasonDrivetrain.commands;

/**
 *
 * @author bradmiller
 */
public class DriveTeleop extends CommandBase {

    public DriveTeleop() {
        requires(drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
        drivetrain.cheesyDrive(oi.getJoystickThrottle(), oi.getJoystickCurve());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        drivetrain.tankDrive(0, 0);
    }
}
