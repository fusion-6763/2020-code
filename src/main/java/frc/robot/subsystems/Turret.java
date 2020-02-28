/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.TURRET_PORT;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private final CANSparkMax _turretMotor = new CANSparkMax(TURRET_PORT, MotorType.kBrushless);
  private final CANEncoder _turretEncoder = _turretMotor.getEncoder();

  /**
   * Creates a new Turret.
   */
  public Turret() {
    setDefaultCommand(new RunCommand(this::stop, this));
    _turretEncoder.setPosition(0);
  }

  public void stop(){
    _turretMotor.stopMotor();
  }

  public void set(double value) {
    _turretMotor.set(value);
  }

  public double getEncoder(){
    return _turretEncoder.getPosition();
  }

  public void resetEncoder(){
    _turretEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    NetworkTableInstance.getDefault().getTable("shuffleboard").getEntry("turretEncoder").setNumber(_turretEncoder.getPosition());
  }
}
