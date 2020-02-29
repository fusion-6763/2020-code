/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

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
    public static final int LEFT_MOTOR_PORT_1 = 1;
    public static final int LEFT_MOTOR_PORT_2 = 2;
    public static final int RIGHT_MOTOR_PORT_1 = 3;
    public static final int RIGHT_MOTOR_PORT_2 = 4;

    public static final boolean LEFT_ENCODER_REVERSED = false;
    public static final boolean RIGHT_ENCODER_REVERSED = true;

    public static final int ENCODER_COUNTS_PER_ROTATION = 1024;
    public static final double WHEEL_DIAMETER = 6; // In inches
    public static final double ENCODER_DISTANCE_PER_PULSE = (WHEEL_DIAMETER * Math.PI)
        / (double) ENCODER_COUNTS_PER_ROTATION;
  }

  public static final class IntakeConstants {
    public static final int INTAKE_ARM_PORT = 6;
    public static final int INTAKE_SUCC_PORT = 3;

    // Encoder ticks per rotation = 1024, 90 degrees is equal to 1/4 of a rotation.
    public static final int ENCODER_DISTANCE_TO_90DEG = 1024 / 4;

    public static final int HOPPER_PORT = 1;
    public static final double HOPPER_SPEED = 0.6;

    public static final int TOWER_PORT = 2;

    public static final double TURRET_CENTERED = 16;
    public static final double TURRET_END = 19;
  }

  public static final class HopperConstants {
  }

  public static final class TowerConstants {
  }

  public static final class ShooterConstants {
    public static final int SHOOTER_PORT1 = 7;
    public static final int SHOOTER_PORT2 = 8;
    public static final int TURRET_PORT = 5;
    public static final int MAX_NEO_RPM = 5700;
    public static final double POWER_PORT_HEIGHT_FT = 8.1875;
    public static final double ROBOT_HEIGHT_FT = 2;
    public static final double LIMELIGHT_ANGLE = 45;
    public static final double SHOOTER_ANGLE = 45;
    public static final double ROLLER_DIAMETER_FT = 0.25;
    public static final double INNER_PORT_DEPTH_FT = 2.4375;
    public static final double DISTANCE_OFFSET_FT = INNER_PORT_DEPTH_FT + 2;
    public static final double GRAVITY_FT_SEC_2 = 32.174;

    // tx: Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    public static final int LIMELIGHT_X_RANGE = 54;
  }

  public static final class ControllerConstants {
    public static final int DRIVER_PORT = 0;
    public static final int SHOOTER_PORT = 1;
  }

  public static final class WheelColors {
    public static final Color BLUE = new Color(0.143, 0.427, 0.429);
    public static final Color GREEN = new Color(0.197, 0.561, 0.240);
    public static final Color RED = new Color(0.561, 0.232, 0.114);
    public static final Color YELLOW = new Color(0.361, 0.524, 0.113);
  }

  public static final class CameraConstants {
    public static final String CHAMELEON_CAMERA_NAME = "BallTracker";
  }
}
