/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turret;


public class Teleop extends CommandBase {
  private XboxController _driveStick;
  private DriveTrain _myRobot;
  private Turret _turret;
  private Joystick _operatorController;
  private Arm _arm;
  private Limelight _limelight;

  private final Joystick _guitarHero1 = new Joystick(4);

  /**
   * Creates a new Teleop.
   */
  public Teleop(final DriveTrain driveTrain, final Turret turret, final XboxController driveStick, final Joystick operatorController, final Arm arm, final Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    _myRobot = driveTrain;
    _driveStick = driveStick;
    _turret = turret;
    _operatorController = operatorController;
    _arm = arm;
    _limelight = limelight;

    addRequirements(_myRobot, arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //_limelight.setDriverMode(true);
    

    double GHForward = 0;
    double GHRotation = 0;
    if (_guitarHero1.getPOV() == 225) {
      GHForward = -0.6;
    }
    else if (_guitarHero1.getPOV() == 315) {
      GHForward = 0.6;
    }

    if (_guitarHero1.getRawButton(1)) {
      GHRotation = 0.5;
    }
    else if (_guitarHero1.getRawButton(2)) {
      GHRotation = -0.5;
    }
    System.out.println(GHForward != 0 || GHRotation != 0);
    SmartDashboard.putBoolean("GuitarUsed", GHForward != 0 || GHRotation != 0);
    if (GHForward != 0 || GHRotation != 0) {
      _myRobot.drive(GHForward, GHRotation);
    }
    else if (_driveStick.getRawButton(2)){ //Reverses robot driving if button is pressed. Button 2 = B
      _myRobot.drive(-_driveStick.getRawAxis(1)*0.8, _driveStick.getRawAxis(0)*0.5);
    }
    else if(_driveStick.getRawButton(5)){ // Left Bumper pressed = slow down
      _myRobot.drive(_driveStick.getRawAxis(1)*0.5, _driveStick.getRawAxis(0)*0.5);
    }
    else if (_guitarHero1.getRawButton(4)){
      _myRobot.drive(.8, 0);
    } //star power
    else{
      _myRobot.drive(_driveStick.getRawAxis(1)*0.8, _driveStick.getRawAxis(0)*0.5);
    }
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
