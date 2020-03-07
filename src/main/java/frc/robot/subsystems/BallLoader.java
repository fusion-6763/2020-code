/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.HOPPER_SPEED;
import static frc.robot.Constants.IntakeConstants.LOADER_PORT;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallLoader extends SubsystemBase {
  private final CANSparkMax coolMotor = new CANSparkMax(LOADER_PORT, MotorType.kBrushless);

  public BallLoader() {
    setDefaultCommand(new RunCommand(this::neutral, this));
  }

  public void in() {
    coolMotor.set(-HOPPER_SPEED);
  }

  public void out(){
    coolMotor.set(HOPPER_SPEED);
  }

  public void neutral() {
    coolMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
