/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class ChameleonVision {
  NetworkTable table;

  public ChameleonVision(String cameraName) {
    table = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable(cameraName);
  }

  public double getX() {
    return table.getEntry("yaw").getDouble(0.0);
  }

  public double getY() {
    return table.getEntry("pitch").getDouble(0.0);
  }

  public boolean getIsValid() {
    return table.getEntry("is_valid").getBoolean(false);
  }

  public void setDriverMode(boolean driverMode) {
    table.getEntry("driver_mode").setBoolean(driverMode);
  }
}
