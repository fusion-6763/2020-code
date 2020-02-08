/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import static frc.robot.Constants.ShooterConstants.LIMELIGHT_ANGLE;
import static frc.robot.Constants.ShooterConstants.POWER_PORT_HEIGHT_FT;
import static frc.robot.Constants.ShooterConstants.ROBOT_HEIGHT_FT;

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

  public void setDriverMode(boolean driverMode) {
    if (driverMode) {
      table.getEntry("camMode").setDouble(1.0);
    } else {
      table.getEntry("camMode").setDouble(0.0);
    }
  }

  public void setPipeline(int pipeline) {
    table.getEntry("pipeline").setDouble(pipeline);
  }

  public void setLights(LightMode mode) {
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
