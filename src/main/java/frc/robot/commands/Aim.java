/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.Limelight.LightMode;
import frc.robot.subsystems.Turret;

import static frc.robot.Constants.IntakeConstants.TURRET_END;

public class Aim extends CommandBase {
  private final Turret _turret;
  private final Limelight _limelight;

  /**
   * Creates a new Aim.
   */
  public Aim(final Turret turret, final Limelight limelight) {
    _turret = turret;
    _limelight = limelight;

    addRequirements(_turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _limelight.setLights(LightMode.ON);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(_limelight.getX() < 0){
      _turret.set(0.04);
    }
    else if(_limelight.getX() > 0){
      _turret.set(-0.04);
    }
    else{
      _turret.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _turret.stop();

    _limelight.setLights(LightMode.DEFAULT);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(_turret.getEncoder() <= 0 && _limelight.getX() > 0){
      System.out.println("Too far right");

      return true;
    }
    else if(_turret.getEncoder() >= TURRET_END && _limelight.getX() < 0){
      System.out.println("Too far left");

      return true;
    }
    else if(_limelight.getX() > -3 && _limelight.getX() < 3){
      return true;
    }
    else{
      return false;
    }
  }
}
