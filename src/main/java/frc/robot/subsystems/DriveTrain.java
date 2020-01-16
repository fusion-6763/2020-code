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
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private final Spark _leftMotor = new Spark(LEFT_MOTOR_PORT);
  private final Spark _rightMotor = new Spark(RIGHT_MOTOR_PORT);

  private final DifferentialDrive _myRobot = new DifferentialDrive(_leftMotor, _rightMotor);

  private final Encoder _leftEncoder = new Encoder(LEFT_ENCODER_PORTS[0], LEFT_ENCODER_PORTS[1]);
  private final Encoder _rightEncoder = new Encoder(RIGHT_ENCODER_PORTS[0], RIGHT_ENCODER_PORTS[1]);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    _leftEncoder.setReverseDirection(LEFT_ENCODER_REVERSED);
    _rightEncoder.setReverseDirection(RIGHT_ENCODER_REVERSED);

    _leftEncoder.reset();
    _rightEncoder.reset();

    setDefaultCommand(new RunCommand(this::stop, this));
  }

  public void drive(final double speed, final double rotationSpeed) {
    _myRobot.arcadeDrive(speed, rotationSpeed);
  }

  public int getLeftEncoder() {
    return _leftEncoder.get();
  }

  public int getRightEncoder() {
    return _rightEncoder.get();
  }

  public int getEncoders() {
    return Math.round((getLeftEncoder() + getRightEncoder()) / 2);
  }

  public void resetEncoders(final boolean left, final boolean right) {
    if (left) {
      _leftEncoder.reset();
    }
    if (right) {
      _rightEncoder.reset();
    }
  }

  public void stop() {
    _myRobot.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
