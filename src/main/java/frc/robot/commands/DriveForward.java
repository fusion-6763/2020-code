/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveForward extends CommandBase {
  DriveTrain driveTrain;
  Mode mode;
  double value;
  double speed;

  Timer timer;

  /**
   * Creates a new DriveForward.
   */
  public DriveForward(DriveTrain _driveTrain, Mode _mode, double _value, double _speed) {
    driveTrain = _driveTrain;
    value = _value;
    speed = _speed;
    mode = _mode;

    if (mode == Mode.TIME) {
      timer = new Timer();
    }

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (mode == Mode.TIME) {
      timer.start();
    } else if (mode == Mode.DISTANCE) {
      driveTrain.resetEncoders(true, true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drive(speed, 0.0);
    System.out.println("Run");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (mode == Mode.DISTANCE && driveTrain.getLeftEncoder() >= value) {
      return true;
    } else if (mode == Mode.TIME && timer.get() >= value) {
      return true;
    } else {
      return false;
    }
  }

  public enum Mode {
    TIME, DISTANCE
  }
}
