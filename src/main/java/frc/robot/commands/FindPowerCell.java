/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.ChameleonVision;
import frc.robot.subsystems.DriveTrain;

public class FindPowerCell extends CommandBase {
  private final DriveTrain _driveTrain;
  private final ChameleonVision _camera;

  /**
   * Creates a new FindPowerCell.
   */
  public FindPowerCell(final DriveTrain driveTrain, final ChameleonVision camera) {
    _driveTrain = driveTrain;
    _camera = camera;
    addRequirements(_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final double speed = _camera.getX() / 30;
    _driveTrain.drive(0, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _driveTrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    final double position = _camera.getX();
    return position < 2 && position > -2;
  }
}
