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

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import java.awt.Button;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Aim;
import frc.robot.commands.FindPowerCell;
import frc.robot.commands.Intake;
import frc.robot.commands.Shoot;
import frc.robot.commands.Teleop;
import frc.robot.commands.RunHopper;
import frc.robot.commands.LoadBall;
import frc.robot.commands.RunArm;
import frc.robot.commands.RunArm.ArmMode;
import frc.robot.commands.automodes.JustDriveAuto;
import frc.robot.commands.automodes.SideAuto;
import frc.robot.commands.automodes.SimpleAuto;
import frc.robot.sensors.ChameleonVision;
import frc.robot.sensors.DriveCamera;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
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

  private final Joystick _shooterController = new Joystick(SHOOTER_PORT);
  private final JoystickButton _trigger = new JoystickButton(_shooterController, 1);
  private final JoystickButton _thumbButton = new JoystickButton(_shooterController, 2);
  private final JoystickButton _topButton0 = new JoystickButton(_shooterController, 3);
  private final JoystickButton _topButton1 = new JoystickButton(_shooterController, 4);
  private final JoystickButton _topButton2 = new JoystickButton(_shooterController, 5);
  private final JoystickButton _topButton3 = new JoystickButton(_shooterController, 6);

  private final DriveCamera _driveCamera = new DriveCamera();
  public final ChameleonVision _ballTracker = new ChameleonVision(CHAMELEON_CAMERA_NAME);
  public final Limelight _limelight = new Limelight();

  // The robot's subsystems and commands are defined here.
  private final DriveTrain _driveTrain = new DriveTrain();
  private final BallIntake _ballIntake = new BallIntake();
  private final Shooter _shooter = new Shooter(_limelight);
  private final Turret _turret = new Turret();
  private final Hopper _hopper = new Hopper();
  private final Tower _tower = new Tower();

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
    //_leftShooterBumper.whenHeld(new Intake(_ballIntake));
    //_rightShooterBumper.whenHeld(new Shoot(_shooter));
    _xButton.whenPressed(new FindPowerCell(_driveTrain, _ballTracker, _driverController));
    _trigger.whenHeld(new SequentialCommandGroup(new Shoot(_shooter).withTimeout(0.5), new ParallelCommandGroup(new Shoot(_shooter), new RunHopper(_hopper), new LoadBall(_tower))));
    _thumbButton.whenHeld(new ParallelCommandGroup(new RunArm(_ballIntake, ArmMode.DOWN), new Intake(_ballIntake)));
    _thumbButton.whenInactive(new RunArm(_ballIntake, ArmMode.UP));
    ((edu.wpi.first.wpilibj2.command.button.Button) _topButton0.or(_topButton1).or(_topButton2).or(_topButton3))
        .whenHeld(new Aim(_turret, _limelight));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    double mode = NetworkTableInstance.getDefault().getTable("frcdashboard").getEntry("auto").getDouble(-1);

    if(mode == 1){
      // SimpleAuto
      return new SimpleAuto(_driveTrain, _shooter, _hopper, _tower, _limelight, _turret);
    }
    else if(mode == 2){
      // SideAuto
      return new SideAuto(_driveTrain, _shooter, _ballIntake, _hopper, _tower, _turret, _limelight);
    }
    else{
      return new JustDriveAuto(_driveTrain);
    }
  }

  public Command getTeleopCommand() {
    return new Teleop(_driveTrain, _turret, _driverController, _shooterController);
  }
}