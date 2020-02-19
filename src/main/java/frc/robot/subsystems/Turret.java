/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.TURRET_PORT;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private final CANSparkMax _turretMotor = new CANSparkMax(TURRET_PORT, MotorType.kBrushless);

  /**
   * Creates a new Turret.
   */
  public Turret() {
    setDefaultCommand(new RunCommand(this::stop, this));
  }

  public void stop(){
    _turretMotor.stopMotor();
  }

  public void set(double value) {
    _turretMotor.set(value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
