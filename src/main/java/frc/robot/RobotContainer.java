/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.ControllerConstants.DRIVER_PORT;
import static frc.robot.Constants.ControllerConstants.SHOOTER_PORT;
import static frc.robot.Constants.CameraConstants.CHAMELEON_CAMERA_NAME;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Intake;
import frc.robot.commands.automodes.LimelightTestAuto;
import frc.robot.commands.Shoot;
import frc.robot.commands.Teleop;
import frc.robot.commands.automodes.TestAuto;
import frc.robot.sensors.ChameleonVision;
import frc.robot.sensors.DriveCamera;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController _driverController = new XboxController(DRIVER_PORT);
  private final XboxController _shooterController = new XboxController(SHOOTER_PORT);
  private final JoystickButton _leftShooterBumper = new JoystickButton(_shooterController,
      XboxController.Button.kBumperLeft.value);
  private final JoystickButton _rightShooterBumper = new JoystickButton(_shooterController,
      XboxController.Button.kBumperRight.value);

  private final DriveCamera _driveCamera = new DriveCamera();
  private final ChameleonVision _ballTracker = new ChameleonVision(CHAMELEON_CAMERA_NAME);
  public final Limelight _limelight = new Limelight();

  // The robot's subsystems and commands are defined here...
  private final DriveTrain _driveTrain = new DriveTrain();
  private final BallIntake _ballIntake = new BallIntake();
  private final Shooter _shooter = new Shooter();

  private final Command _driveCommand = new LimelightTestAuto(_driveTrain, _limelight);

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
    _leftShooterBumper.whenHeld(new Intake(_ballIntake));
    _rightShooterBumper.whenHeld(new Shoot(_shooter));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return _driveCommand;
  }

  public Command getTeleopCommand() {
    return new Teleop(_driveTrain, _driverController);
  }
}