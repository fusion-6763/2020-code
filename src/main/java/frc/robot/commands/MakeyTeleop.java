/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MakeyTeleop extends CommandBase {
  DriveTrain _driveTrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("frcdashboard").getSubTable("makey");

  /**
   * Creates a new MakeyTeleop.
   */
  public MakeyTeleop(DriveTrain driveTrain) {
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
    boolean leftPressed = table.getEntry("leftPressed").getBoolean(false);
    boolean upPressed = table.getEntry("upPressed").getBoolean(false);
    boolean rightPressed = table.getEntry("rightPressed").getBoolean(false);
    boolean downPressed = table.getEntry("downPressed").getBoolean(false);

    double speed = 0.0;
    double turnSpeed = 0.0;

    if (leftPressed) {
      turnSpeed = -0.5;
    } else if (rightPressed) {
      turnSpeed = 0.5;
    }

    if (upPressed) {
      speed = 0.5;
    } else if (downPressed) {
      speed = -0.5;
    }

    _driveTrain.drive(speed, turnSpeed);
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
