/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.CameraConstants.CAMERA_PORTS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * This class defines the camera used for vision tracking.
 */
public class TrackingCamera extends SubsystemBase {
  private final int _port1 = CAMERA_PORTS[0];

  public TrackingCamera() {
  }

  @Override
  public void periodic() {
    super.periodic();
    // TODO: Get the current information from the camera
  }
}
