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
    return table.getEntry("ty").getDouble(0);
  }

  public boolean isValid() {
    return table.getEntry("tv").getDouble(0) == 1;
  }

  public double getDistance() {
    final double targetAngle = getY();
    return (POWER_PORT_HEIGHT_FT - ROBOT_HEIGHT_FT) / Math.tan(LIMELIGHT_ANGLE + targetAngle);
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
    final double x = getDistance() + DISTANCE_OFFSET_FT;
    final double tanA = Math.tan(SHOOTER_ANGLE);
    final double cosA = Math.cos(SHOOTER_ANGLE);
    final double numerator = 2 * ((x * tanA) + ROBOT_HEIGHT_FT - POWER_PORT_HEIGHT_FT);
    final double denominator = (cosA * cosA) * (x * x) * GRAVITY_FT_SEC_2;
    // return Math.sqrt(numerator / denominator);
    return Math.sqrt((x * GRAVITY_FT_SEC_2) / Math.sin(2 * SHOOTER_ANGLE));
  }

  public double getRPM() {
    final double velocity = getVelocity();
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
