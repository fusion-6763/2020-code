/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Aim extends CommandBase {
  private final Shooter _shooter;

  /**
   * Creates a new Aim.
   */
  public Aim(final Shooter shooter) {
    _shooter = shooter;
    addRequirements(_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO
    //_shooter.rotate();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _shooter.neutral();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    final double position = _shooter.getX();
    return position < 2 && position > -2;
  }
}
