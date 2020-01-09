/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop extends CommandBase {
  Joystick driveStick;
  DriveTrain myRobot;

  /**
   * Creates a new Teleop.
   */
  public Teleop(DriveTrain _driveTrain, Joystick _driveStick) {
    // Use addRequirements() here to declare subsystem dependencies.
    myRobot = _driveTrain;
    driveStick = _driveStick;

    addRequirements(myRobot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    myRobot.drive(driveStick.getY(), driveStick.getX());
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
