/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.DriveConstants.LEFT_MOTOR_PORT_1;
import static frc.robot.Constants.DriveConstants.LEFT_MOTOR_PORT_2;
import static frc.robot.Constants.DriveConstants.RIGHT_MOTOR_PORT_1;
import static frc.robot.Constants.DriveConstants.RIGHT_MOTOR_PORT_2;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax _leftMotor1 = new CANSparkMax(LEFT_MOTOR_PORT_1, MotorType.kBrushless);
  private final CANSparkMax _leftMotor2 = new CANSparkMax(LEFT_MOTOR_PORT_2, MotorType.kBrushless);
  private final CANSparkMax _rightMotor1 = new CANSparkMax(RIGHT_MOTOR_PORT_1, MotorType.kBrushless);
  private final CANSparkMax _rightMotor2 = new CANSparkMax(RIGHT_MOTOR_PORT_2, MotorType.kBrushless);
  private final SpeedControllerGroup _leftSide = new SpeedControllerGroup(_leftMotor1, _leftMotor2);
  private final SpeedControllerGroup _rightSide = new SpeedControllerGroup(_rightMotor1, _rightMotor2);

  private final DifferentialDrive _myRobot = new DifferentialDrive(_leftSide, _rightSide);

  private final CANEncoder _leftEncoder;
  private final CANEncoder _rightEncoder;

  private final AHRS _navx = new AHRS(SPI.Port.kMXP);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    _leftEncoder = _leftMotor1.getEncoder();
    _rightEncoder = _rightMotor1.getEncoder();

    _leftEncoder.setPosition(0);
    _rightEncoder.setPosition(0);

    _leftMotor1.restoreFactoryDefaults();
    _leftMotor2.restoreFactoryDefaults();
    _rightMotor1.restoreFactoryDefaults();
    _rightMotor2.restoreFactoryDefaults();

    setDefaultCommand(new RunCommand(this::stop, this));
  }

  /**
   * Drives the robot.
   *
   * @param speed         The robot's speed along the X axis
   * @param rotationSpeed The speed the robot rotates along the Z axis
   */
  public void drive(final double speed, final double rotationSpeed) {
    _myRobot.arcadeDrive(speed, rotationSpeed);
  }

  public double getLeftEncoder() {
    return _leftEncoder.getPosition();
  }

  public double getRightEncoder() {
    return _rightEncoder.getPosition();
  }

  public long getEncoders() {
    return Math.round((getLeftEncoder() + getRightEncoder()) / 2);
  }

  public void resetEncoders(final boolean left, final boolean right) {
    if (left) {
      _leftEncoder.setPosition(0);
    }
    if (right) {
      _rightEncoder.setPosition(0);
    }
  }

  public double getAngle() {
    return _navx.getAngle();
  }

  public void resetGyro() {
    _navx.reset();
  }

  public void stop() {
    _myRobot.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
