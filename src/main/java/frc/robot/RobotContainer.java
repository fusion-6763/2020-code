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

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Aim;
import frc.robot.commands.BackHopper;
import frc.robot.commands.Intake;
import frc.robot.commands.LoadBall;
import frc.robot.commands.Outtake;
import frc.robot.commands.RunHopper;
import frc.robot.commands.Shoot;
import frc.robot.commands.Teleop;
import frc.robot.commands.UnloadBall;
import frc.robot.commands.automodes.SimpleAuto;
import frc.robot.sensors.ChameleonVision;
import frc.robot.sensors.DriveCamera;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.Limelight.LightMode;
import frc.robot.subsystems.Arm;
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
  private final Hopper _hopper = new Hopper();
  private final Tower _tower = new Tower();
  
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

    _trigger.whenHeld(
      new ParallelCommandGroup(
        new Aim(_turret, _limelight),
        new Shoot(_shooter)
      )
    ).whenReleased(new InstantCommand(() -> _limelight.setLights(LightMode.DEFAULT)));

    _topButton0.whenHeld(new RunHopper(_hopper));
    _xButton.whenHeld(new BackHopper(_hopper));

    _topButton2.whenHeld(new LoadBall(_tower));
    _topButton3.whenHeld(new UnloadBall(_tower));

    _topButton1.whenPressed(
      new SequentialCommandGroup(
        new InstantCommand(() -> _limelight.setLights(LightMode.ON)),
        new WaitCommand(0.3),
        new Aim(_turret, _limelight),
        new InstantCommand(() -> _limelight.setLights(LightMode.DEFAULT))
      )
    );

    _7.whenPressed(new RunCommand(()-> _shooter.speed(0.2), _shooter));
    _8.whenPressed(new RunCommand(() -> _shooter.speed(0.4), _shooter));
    _9.whenPressed(new RunCommand(() -> _shooter.speed(0.6), _shooter));
    _10.whenPressed(new RunCommand(() -> _shooter.speed(0.8), _shooter));
    _11.whenPressed(new RunCommand(() -> _shooter.speed(1), _shooter));
    _12.whenPressed(new Aim(_turret, _limelight));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    /*double mode = NetworkTableInstance.getDefault().getTable("frcdashboard").getEntry("auto").getDouble(-1);

    if(mode == 1){
      // SimpleAuto
      return new SimpleAuto(_driveTrain, _shooter, _hopper, _tower, _limelight, _turret);
    }
    else if(mode == 2){
      // SideAuto
      return new SideAuto(_driveTrain, _shooter, _ballIntake, _hopper, _tower, _turret, _limelight, _arm);
    }
    else{
      return new JustDriveAuto(_driveTrain);
    }*/

    return new SimpleAuto(_arm, _driveTrain, _shooter, _hopper, _tower, _limelight, _turret);
  }

  public Command getTeleopCommand() {
    return new Teleop(_driveTrain, _turret, _driverController, _shooterController, _arm);
  }
}