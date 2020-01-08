/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  public Spark leftMotor = new Spark(Constants.DriveConstants.leftMotorPort1);
  public Spark rightMotor = new Spark(Constants.DriveConstants.rightMotorPort1);

  DifferentialDrive myRobot = new DifferentialDrive(leftMotor, rightMotor);

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
  }

  public void drive(Double speed, Double rotationSpeed){
    myRobot.arcadeDrive(speed, rotationSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
