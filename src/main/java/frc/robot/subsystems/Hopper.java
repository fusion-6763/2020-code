/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.HOPPER_PORT;
import static frc.robot.Constants.IntakeConstants.HOPPER_SPEED;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  private final Spark hopperMotor = new Spark(HOPPER_PORT);

  public Hopper() {
    setDefaultCommand(new RunCommand(this::neutral, this));
  }

  public void in() {
    hopperMotor.set(HOPPER_SPEED);
  }

  public void neutral() {
    hopperMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
