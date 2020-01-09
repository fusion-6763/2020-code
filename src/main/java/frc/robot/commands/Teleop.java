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
import static frc.robot.Constants.ControllerConstants.DRIVER_PORT;;

public class Teleop extends CommandBase {
  Joystick driveStick = new Joystick(DRIVER_PORT);
  DriveTrain myRobot = new DriveTrain();

  /**
   * Creates a new Teleop.
   */
  public Teleop() {
    // Use addRequirements() here to declare subsystem dependencies.
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
