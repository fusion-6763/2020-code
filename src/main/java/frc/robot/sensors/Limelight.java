/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import static frc.robot.Constants.ShooterConstants.DISTANCE_OFFSET_FT;
import static frc.robot.Constants.ShooterConstants.GRAVITY_FT_SEC_2;
import static frc.robot.Constants.ShooterConstants.LIMELIGHT_ANGLE;
import static frc.robot.Constants.ShooterConstants.POWER_PORT_HEIGHT_FT;
import static frc.robot.Constants.ShooterConstants.ROBOT_HEIGHT_FT;
import static frc.robot.Constants.ShooterConstants.ROLLER_DIAMETER_FT;
import static frc.robot.Constants.ShooterConstants.SHOOTER_ANGLE;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight implements INetworkTablesTrackingCamera {
  private final NetworkTable table;

  public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public double getX() {
    return table.getEntry("tx").getDouble(0);
  }

  public double getY() {
    SmartDashboard.putNumber("Limelight Y", table.getEntry("ty").getDouble(0));
    return table.getEntry("ty").getDouble(0);
  }

  public boolean isValid() {
    return table.getEntry("tv").getDouble(0) == 1;
  }

  public double getDistance() {
    final double targetAngle = getY();
    SmartDashboard.putNumber("Distance To Target",
        (POWER_PORT_HEIGHT_FT - ROBOT_HEIGHT_FT) / Math.tan(LIMELIGHT_ANGLE + targetAngle));
    return Math.abs((POWER_PORT_HEIGHT_FT - ROBOT_HEIGHT_FT) / Math.tan(LIMELIGHT_ANGLE + targetAngle));
  }

  /**
   * Returns the needed velocity of the ball to shoot it into the power port.
   * 
   * All measurements are in feet, degrees and seconds.
   * 
   * v = sqrt((2(x * tan(a) + h - y)/(cos²(a)x²g))
   * 
   * v = sqrt((x * g) / sin(2a))
   * 
   * @see https://en.wikipedia.org/wiki/Projectile_motion#Maximum_height_of_projectile
   * @return the initial velocity of the ball required to reach the target.
   */
  public double getVelocity() {
    final double xFeet = getDistance() + DISTANCE_OFFSET_FT;
    // final double tanA = Math.tan(SHOOTER_ANGLE);
    // final double cosA = Math.cos(SHOOTER_ANGLE);
    // final double numerator = 2 * ((x * tanA) + ROBOT_HEIGHT_FT -
    // POWER_PORT_HEIGHT_FT);
    // final double denominator = (cosA * cosA) * (x * x) * GRAVITY_FT_SEC_2;
    // return Math.sqrt(numerator / denominator);

    // System.out.println(x);
    // System.out.println(x * GRAVITY_FT_SEC_2);
    // System.out.println(Math.sin(2 * SHOOTER_ANGLE));
    // System.out.println(Math.sqrt((x * GRAVITY_FT_SEC_2) / Math.sin(2 *
    // SHOOTER_ANGLE)));
    // System.out.println("-----------");

    // return Math.sqrt((x * GRAVITY_FT_SEC_2) / Math.sin(2 * SHOOTER_ANGLE));

    //final double gFeet = 32.17; // gravity // 9.81
    // final double x = 49; // target x // x is defined above
    //final double yFeet = (POWER_PORT_HEIGHT_FT - ROBOT_HEIGHT_FT); // target y
    //final double o = 45; // launch angle

    // CHANGE VARIABLES FROM FEET TO METERS
    // final double feetInAMeter = 3.28084;
    // final double gMeters = gFeet / feetInAMeter;
    // final double yMeters = yFeet / feetInAMeter;
    // final double xMeters = xFeet / feetInAMeter;

    // final double vMeters = (Math.sqrt(gMeters) * Math.sqrt(xMeters) *
    // Math.sqrt((Math.tan(o)*Math.tan(o))+1)) / Math.sqrt(2 * Math.tan(o) - (2 *
    // gMeters * yMeters) / xMeters); // velocity
    // final double v = vMeters * feetInAMeter;
    // System.out.println(v);

    final double a = 2;

    // All measurements in feet or degrees
    final double launchAngle = SHOOTER_ANGLE; // a
    final double distanceToTarget = 20000; // d
    final double distanceToWall = getDistance() + DISTANCE_OFFSET_FT; // R | Distance to bottom of target
    final double limelightAngle = LIMELIGHT_ANGLE; // B
    final double targetHeight = (POWER_PORT_HEIGHT_FT - ROBOT_HEIGHT_FT); // h
    final double gravity = 32.17; // G

    final double numerator = gravity * distanceToWall * distanceToWall;
    final double denominator1 = ((distanceToWall * distanceToWall * distanceToWall)
        - (3 * distanceToWall * targetHeight * targetHeight)
            / ((distanceToWall * distanceToWall) + (targetHeight * targetHeight)))
        * Math.sin(2) * launchAngle;
    final double denominator2 = ((((3 * targetHeight * distanceToWall * distanceToWall) - (targetHeight * targetHeight))
        / ((distanceToWall * distanceToWall) + (targetHeight * targetHeight))) * Math.cos(2) * launchAngle)
        - targetHeight;
    final double velocity = Math.sqrt(numerator / (denominator1 + denominator2));
    SmartDashboard.putNumber("Target Velocity", velocity);

    return velocity;
  }

  public double getRPM() {
    final double velocity = getVelocity();
    // System.out.println(velocity);
    // System.out.println(ROLLER_DIAMETER_FT * Math.PI);
    // System.out.println(velocity / (ROLLER_DIAMETER_FT * Math.PI));
    return velocity / (ROLLER_DIAMETER_FT * Math.PI);
  }

  public void setDriverMode(final boolean driverMode) {
    if (driverMode) {
      table.getEntry("camMode").setDouble(1.0);
    } else {
      table.getEntry("camMode").setDouble(0.0);
    }
  }

  public void setPipeline(final int pipeline) {
    table.getEntry("pipeline").setDouble(pipeline);
  }

  public void setLights(final LightMode mode) {
    if (mode == LightMode.ON) {
      table.getEntry("ledMode").setDouble(3);
    } else if (mode == LightMode.DEFAULT) {
      table.getEntry("ledMode").setDouble(0);
    } else if (mode == LightMode.OFF) {
      table.getEntry("ledMode").setDouble(1);
    } else if (mode == LightMode.BLINKY) {
      table.getEntry("ledMode").setDouble(2);
    }
  }

  public enum LightMode {
    ON, OFF, DEFAULT, BLINKY
  }
}
