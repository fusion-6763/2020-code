/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.IntakeConstants.TOWER_PORT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class Tower extends SubsystemBase {
  private final VictorSPX towerMotor = new VictorSPX(TOWER_PORT);

  public Tower() {
    setDefaultCommand(new RunCommand(this::neutral, this));
  }

  public void in() {
    towerMotor.set(ControlMode.PercentOutput, -1);
  }

  public void out(){
    towerMotor.set(ControlMode.PercentOutput, 1);
  }

  public void neutral() {
    towerMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
