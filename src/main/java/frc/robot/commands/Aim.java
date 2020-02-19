/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.Turret;

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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(_limelight.getX() < -10){
      _turret.set(0.6);
    }
    else if(_limelight.getX() > 10){
      _turret.set(-0.6);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _turret.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(_limelight.getX() > -10 && _limelight.getX() < 10){
      return true;
    }
    else{
      return false;
    }
  }
}
