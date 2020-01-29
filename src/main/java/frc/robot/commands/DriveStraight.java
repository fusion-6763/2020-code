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

public class DriveStraight extends CommandBase {
  private DriveTrain _driveTrain;
  private Mode _mode;
  private double _limit;
  private double _speed;

  private Timer _timer;

  /**
   * Creates a new DriveStraight.
   */
  public DriveStraight(final DriveTrain driveTrain, final Mode mode, final double limit, final double speed) {
    _driveTrain = driveTrain;
    _mode = mode;
    _limit = limit;
    _speed = speed;

    if (mode == Mode.TIME) {
      _timer = new Timer();
    }

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (_mode == Mode.TIME) {
      _timer.start();
    } else if (_mode == Mode.DISTANCE) {
      _driveTrain.resetEncoders(true, true);
    }

    _driveTrain.resetGyro();
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   */
  @Override
  public void execute() {

    // If the angle is > 2 degrees, the robot is turned to the right.
    // Turn left to fix the offset, which is counterclockwise.
    if (_driveTrain.getAngle() > 2) {
      _driveTrain.drive(_speed, -0.4);
    }

    // If the angle is < -2 degrees, the robot is turned to the left.
    // Turn right to fix the offset, which is clockwise.
    else if (_driveTrain.getAngle() < -2) {
      _driveTrain.drive(_speed, 0.4);
    }

    // If the robot isn't crooked, just drive straight.
    else {
      _driveTrain.drive(_speed, 0.0);
    }
    System.out.println("Run");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_mode == Mode.DISTANCE && _driveTrain.getEncoders() >= _limit) {
      return true;
    } else if (_mode == Mode.TIME && _timer.get() >= _limit) {
      return true;
    } else {
      return false;
    }
  }

  public enum Mode {
    TIME, DISTANCE
  }
}
