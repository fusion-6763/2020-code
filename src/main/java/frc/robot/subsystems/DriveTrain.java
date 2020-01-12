/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.DriveConstants.LEFT_ENCODER_PORTS;
import static frc.robot.Constants.DriveConstants.LEFT_ENCODER_REVERSED;
import static frc.robot.Constants.DriveConstants.LEFT_MOTOR_PORT;
import static frc.robot.Constants.DriveConstants.RIGHT_ENCODER_PORTS;
import static frc.robot.Constants.DriveConstants.RIGHT_ENCODER_REVERSED;
import static frc.robot.Constants.DriveConstants.RIGHT_MOTOR_PORT;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  public Spark leftMotor = new Spark(LEFT_MOTOR_PORT);
  public Spark rightMotor = new Spark(RIGHT_MOTOR_PORT);

  DifferentialDrive myRobot = new DifferentialDrive(leftMotor, rightMotor);

  Encoder leftEncoder = new Encoder(LEFT_ENCODER_PORTS[0], LEFT_ENCODER_PORTS[1]);
  Encoder rightEncoder = new Encoder(RIGHT_ENCODER_PORTS[0], RIGHT_ENCODER_PORTS[1]);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    leftEncoder.setReverseDirection(LEFT_ENCODER_REVERSED);
    rightEncoder.setReverseDirection(RIGHT_ENCODER_REVERSED);

    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void drive(final double speed, final double rotationSpeed) {
    myRobot.arcadeDrive(speed, rotationSpeed);
  }

  public int getLeftEncoder() {
    return leftEncoder.get();
  }

  public int getRightEncoder() {
    return rightEncoder.get();
  }

  public void resetEncoders(final boolean left, final boolean right) {
    if (left) {
      leftEncoder.reset();
    }
    if (right) {
      rightEncoder.reset();
    }
  }

  public void stop() {
    myRobot.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
