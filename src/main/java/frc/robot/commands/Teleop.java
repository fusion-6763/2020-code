/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turret;

public class Teleop extends CommandBase {
  private XboxController _driveStick;
  private DriveTrain _myRobot;
  private Turret _turret;
  private Joystick _operatorController;
  private Arm _arm;

  /**
   * Creates a new Teleop.
   */
  public Teleop(final DriveTrain driveTrain, final Turret turret, final XboxController driveStick, final Joystick operatorController, final Arm arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    _myRobot = driveTrain;
    _driveStick = driveStick;
    _turret = turret;
    _operatorController = operatorController;
    _arm = arm;

    addRequirements(_myRobot, arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (_driveStick.getRawButton(2)){ //Reverses robot driving if button is pressed. Button 2 = B
      _myRobot.drive(-_driveStick.getRawAxis(1)*0.8, _driveStick.getRawAxis(0)*0.5);
    }
    else{
      _myRobot.drive(_driveStick.getRawAxis(1)*0.8, _driveStick.getRawAxis(0)*0.5);
    }

    _arm.set(_driveStick.getRawAxis(5)*0.3);
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
