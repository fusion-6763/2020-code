/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.ColorShim;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int LEFT_MOTOR_PORT = 0;
        public static final int RIGHT_MOTOR_PORT = 2;

        public static final int SHOOTER_PORT = 3;

        public static final int[] LEFT_ENCODER_PORTS = { 0, 1 };
        public static final int[] RIGHT_ENCODER_PORTS = { 2, 3 };
        public static final boolean LEFT_ENCODER_REVERSED = false;
        public static final boolean RIGHT_ENCODER_REVERSED = true;

        public static final int ENCODER_COUNTS_PER_ROTATION = 1024;
        public static final double WHEEL_DIAMETER = 6;
        public static final double ENCODER_DISTANCE_PER_PULSE = (WHEEL_DIAMETER * Math.PI)
                / (double) ENCODER_COUNTS_PER_ROTATION;
    }

    public static final class ControllerConstants {
        public static final int DRIVER_PORT = 0;
        public static final int SHOOTER_PORT = 1;
    }

    public static final class WheelColors {
        public static final Color BLUE = new ColorShim(0.143, 0.427, 0.429);
        public static final Color GREEN = new ColorShim(0.197, 0.561, 0.240);
        public static final Color RED = new ColorShim(0.561, 0.232, 0.114);
        public static final Color YELLOW = new ColorShim(0.361, 0.524, 0.113);
    }
}
