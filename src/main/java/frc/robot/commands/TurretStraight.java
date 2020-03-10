/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

import static frc.robot.Constants.IntakeConstants.TURRET_CENTERED;
import static frc.robot.Constants.IntakeConstants.TURRET_END;

public class TurretStraight extends CommandBase {
  private Turret _turret;

  /**
   * Creates a new TurretStraight.
   */
  public TurretStraight(Turret turret) {
    // Use addRequirements() here to declare subsystem dependencies.

    _turret = turret;
    addRequirements(_turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(_turret.getEncoder() < TURRET_CENTERED - 1){
      _turret.set(0.07);
    }
    else if(_turret.getEncoder() > TURRET_CENTERED + 1){
      _turret.set(-0.07);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(_turret.getEncoder() < TURRET_CENTERED + 1 && _turret.getEncoder() > TURRET_CENTERED - 1){
      return true;
    }
    else{
      return false;
    }
  }
}
