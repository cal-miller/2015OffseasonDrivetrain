
package org.bitbuckets.OffseasonDrivetrain.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import org.bitbuckets.OffseasonDrivetrain.RobotMap;
import org.bitbuckets.OffseasonDrivetrain.Constants;
        
public class Drivetrain extends Subsystem {
    private Victor driveLeftVictor = new Victor(RobotMap.DRIVE_LEFT);
    private Victor driveRightVictor = new Victor(RobotMap.DRIVE_RIGHT);
    private RobotDrive drivetrain = new RobotDrive(driveLeftVictor, driveRightVictor);
    private Gyro gyro = new Gyro(RobotMap.GYRO);
    private Encoder enc_left = new Encoder(RobotMap.ENCODER_LEFT_A, RobotMap.ENCODER_LEFT_B);
    private Encoder enc_right = new Encoder(RobotMap.ENCODER_RIGHT_A, RobotMap.ENCODER_RIGHT_B);
    
    public Drivetrain() {
        enc_left.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);
        enc_right.setDistancePerPulse(Constants.DISTANCE_PER_PULSE);
    }

    public void initDefaultCommand() {
        
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        drivetrain.tankDrive(leftPower, rightPower);
    }
    
    public void cheesyDrive(double throttle, double curve) {
        if(Math.abs(throttle) > Constants.THROTTLE_CUTOFF) {
            curve *= Constants.TURN_GAIN * Math.abs(throttle);
        }
        
        double leftPower = throttle + curve;
        double rightPower = throttle - curve;
        
        tankDrive(leftPower + skim(rightPower), rightPower + skim(leftPower));
    }
    
    private double skim(double val) {
        return (Math.abs(val) > 1)
                ? ((Math.abs(val)>0 ? 1 : -1) - val) * Constants.SKIM_GAIN
                : 0;    
    }
    
    public void resetEncoders() {
        enc_left.reset();
        enc_right.reset();
    }
    
    public double getLeftDistance() {
        return enc_left.getDistance();
    }
    
    public double getRightDistance() {
        return enc_right.getDistance();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    
    public double getAngleDegrees() {
        return gyro.getAngle();
    }
    
    public double getAngleRadians() {
        return gyro.getAngle() * 2.0 * Math.PI / 360.0;
    }
}

