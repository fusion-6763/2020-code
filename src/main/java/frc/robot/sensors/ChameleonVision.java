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
public class ChameleonVision implements INetworkTablesTrackingCamera {
  NetworkTable table;

  public ChameleonVision(String cameraName) {
    table = NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable(cameraName);
  }

  public double getX() {
    return table.getEntry("targetYaw").getDouble(0.0);
  }

  public double getY() {
    return table.getEntry("targetPitch").getDouble(0.0);
  }

  public boolean isValid() {
    return table.getEntry("is_valid").getBoolean(false);
  }

  public void setDriverMode(boolean driverMode) {
    table.getEntry("driver_mode").setBoolean(driverMode);
  }

  public void setPipeline(int pipeline) {
    table.getEntry("pipeline").setDouble(pipeline);
  }
}
