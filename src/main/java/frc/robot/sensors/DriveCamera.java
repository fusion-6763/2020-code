/**
 * Copyright 2020 FRC Team 6763
 * 
 * This configures the camera used to display the video feed on the driver's station.
 */

package frc.robot.sensors;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class DriveCamera {
  private UsbCamera _driveCamera;

  public DriveCamera() {
    _driveCamera = CameraServer.getInstance().startAutomaticCapture();
    _driveCamera.setConnectVerbose(1);
    _driveCamera.setResolution(320, 240);
    _driveCamera.setFPS(30);
    _driveCamera.setWhiteBalanceAuto();
    _driveCamera.setBrightness(50);
    _driveCamera.setExposureAuto();
  }
}