/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.CameraConstants.CHAMELEON_CAMERA_NAME;
import static frc.robot.Constants.ControllerConstants.DRIVER_PORT;
import static frc.robot.Constants.ControllerConstants.SHOOTER_PORT;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Aim;
import frc.robot.commands.Intake;
import frc.robot.commands.LoadBall;
import frc.robot.commands.Outtake;
import frc.robot.commands.Shoot;
import frc.robot.commands.Teleop;
import frc.robot.commands.UnloadBall;
import frc.robot.commands.automodes.SimpleAuto;
import frc.robot.commands.automodes.*;
import frc.robot.sensors.ChameleonVision;
import frc.robot.sensors.DriveCamera;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.Limelight.LightMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallLoader;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController _driverController = new XboxController(DRIVER_PORT);
  private final JoystickButton _xButton = new JoystickButton(_driverController, XboxController.Button.kX.value);
  private final JoystickButton _leftBumper = new JoystickButton(_driverController, XboxController.Button.kBumperLeft.value);
  private final JoystickButton _rightBumper = new JoystickButton(_driverController, XboxController.Button.kBumperRight.value);


  private final Joystick _shooterController = new Joystick(SHOOTER_PORT);
  private final JoystickButton _trigger = new JoystickButton(_shooterController, 1);
  private final JoystickButton _thumbButton = new JoystickButton(_shooterController, 2);
  private final JoystickButton _topButton0 = new JoystickButton(_shooterController, 3);
  private final JoystickButton _topButton1 = new JoystickButton(_shooterController, 4);
  private final JoystickButton _topButton2 = new JoystickButton(_shooterController, 5);
  private final JoystickButton _topButton3 = new JoystickButton(_shooterController, 6);

  private final JoystickButton _7 = new JoystickButton(_shooterController, 7);
  private final JoystickButton _8 = new JoystickButton(_shooterController, 8);
  private final JoystickButton _9 = new JoystickButton(_shooterController, 9);
  private final JoystickButton _10 = new JoystickButton(_shooterController, 10);
  private final JoystickButton _11 = new JoystickButton(_shooterController, 11);
  private final JoystickButton _12 = new JoystickButton(_shooterController, 12);

  private final Joystick _guitarHero1 = new Joystick(4);
  private final JoystickButton _GHGreen = new JoystickButton(_guitarHero1, 8);
  private final JoystickButton _GHRed = new JoystickButton(_guitarHero1, 2);
  private final JoystickButton _GHYellow = new JoystickButton(_guitarHero1, 1);
  private final JoystickButton _GHBlue = new JoystickButton(_guitarHero1, 3);
  private final JoystickButton _GHOrange = new JoystickButton(_guitarHero1, 4);

  private final DriveCamera _driveCamera = new DriveCamera();
  public final ChameleonVision _ballTracker = new ChameleonVision(CHAMELEON_CAMERA_NAME);
  public final Limelight _limelight = new Limelight();

  // The robot's subsystems and commands are defined here.
  private final DriveTrain _driveTrain = new DriveTrain();
  private final BallIntake _ballIntake = new BallIntake();
  private final Shooter _shooter = new Shooter(_limelight);

  // | Public for a reason
  // v
  public final Turret _turret = new Turret();
  
  private final BallLoader _ballLoader = new BallLoader();
  
  // | Public for a reason
  // v
  public final Arm _arm = new Arm();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //_xButton.whenPressed(new FindPowerCell(_driveTrain, _ballTracker, _driverController));
    _leftBumper.whenHeld(new Intake(_ballIntake));
    _rightBumper.whenHeld(new Outtake(_ballIntake));

    _GHGreen.whenHeld(new Intake(_ballIntake));
    _GHBlue.whenHeld(new Outtake(_ballIntake));
    

    _trigger.whenHeld(
      new ParallelCommandGroup(
        new Aim(_turret, _limelight, true), // mark made me do it
        new Shoot(_shooter)
      )
    ).whenReleased(new InstantCommand(() -> _limelight.setLights(LightMode.DEFAULT)));

    _topButton0.whenHeld(new RunCommand(() -> _turret.set(0.05), _turret));
    _topButton1.whenHeld(new RunCommand(() -> _turret.set(-0.05), _turret));
    //_topButton1.whenPressed(new Aim(_turret, _limelight, false));
    _topButton2.whenHeld(new LoadBall(_ballLoader));
    _topButton3.whenHeld(new UnloadBall(_ballLoader));

    

    _7.whenHeld(new RunCommand(()-> _shooter.speed(0.3), _shooter));
    _8.whenHeld(new RunCommand(() -> _shooter.speed(0.6), _shooter));
    _9.whenHeld(new RunCommand(() -> _shooter.speed(0.7), _shooter));
    _10.whenHeld(new RunCommand(() -> _shooter.speed(0.8), _shooter));
    _11.whenHeld(new RunCommand(() -> _shooter.speed(0.9), _shooter));
    _12.whenHeld(new RunCommand(() -> _shooter.speed(1), _shooter));

    //_12.whenPressed(new InstantCommand(_turret::resetEncoder));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //double mode = NetworkTableInstance.getDefault().getTable("frcdashboard").getEntry("auto").getDouble(-1);
    _limelight.setDriverMode(false);
    int mode = 2;
    if(mode == 1){
      // SimpleAuto
      return new SimpleAuto(_arm, _driveTrain, _shooter, _ballLoader, _limelight, _turret);
    }
    else if(mode == 2){
      // SideAuto
      // Just kidding its medium now lolololol
      return new MediumAuto(_arm, _driveTrain, _shooter, _ballLoader, _limelight, _turret);
      // return new SideAuto(_driveTrain, _shooter, _ballIntake, _hopper, _tower, _turret, _limelight, _arm);
    }
    else{
      return new JustDriveAuto(_driveTrain);
    }

  }
  public Command getTeleopCommand() {
    return new Teleop(_driveTrain, _turret, _driverController, _shooterController, _arm, _limelight);
  }
}