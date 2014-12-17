
package org.bitbuckets.OffseasonDrivetrain;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
   private Joystick driverJoystick = new Joystick(1);
   
   public double getJoystickThrottle() {
       return driverJoystick.getX();
   }
   
   public double getJoystickCurve() {
       return driverJoystick.getY();
   }
}

