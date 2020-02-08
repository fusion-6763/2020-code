/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automodes;

import static frc.robot.Constants.ShooterConstants.LIMELIGHT_X_RANGE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.DriveTrain;

public class LimelightTestAuto extends CommandBase {
  Limelight _limelight;
  DriveTrain _driveTrain;

  // The ideal size for the object the Limelight sees.
  // Measured in percent of the Limelight's viewport.
  double idealSize = 20;

  /**
   * Creates a new LimelightTestAuto.
   */
  public LimelightTestAuto(DriveTrain driveTrain, Limelight limelight) {
    _limelight = limelight;
    _driveTrain = driveTrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Turning stuffs
    double turnSpeed = _limelight.getX() / LIMELIGHT_X_RANGE;

    // Forward/back stuffs
    double driveSpeed = 0.0;

    // Actually drive
    _driveTrain.drive(driveSpeed, turnSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
