/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TurnToAngle extends CommandBase {
  private DriveTrain _driveTrain;
  private double _angle;
  private double _speed;

  /**
   * Creates a new TurnToAngle.
   */
  public TurnToAngle(final DriveTrain driveTrain, final double angle, final double speed) {
    // Use addRequirements() here to declare subsystem dependencies.

    _driveTrain = driveTrain;
    _angle = angle;
    _speed = speed;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _driveTrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _driveTrain.drive(0.0, _angle < 0 ? -Math.abs(_speed) : Math.abs(_speed));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double realAngle = _driveTrain.getAngle();

    if (realAngle <= _angle + 3 && realAngle >= _angle - 3) {
      return true;
    } else {
      return false;
    }
  }
}
