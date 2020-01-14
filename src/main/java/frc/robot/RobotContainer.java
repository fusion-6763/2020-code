/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.ControllerConstants.DRIVER_PORT;
import static frc.robot.Constants.ControllerConstants.SHOOTER_PORT;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Intake;
import frc.robot.commands.Teleop;
import frc.robot.commands.automodes.TestAuto;
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
  private final XboxController driverController = new XboxController(DRIVER_PORT);
  private final XboxController shooterController = new XboxController(SHOOTER_PORT);
  final JoystickButton leftShooterBumper = new JoystickButton(shooterController,
      XboxController.Button.kBumperLeft.value);

  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final BallIntake m_ballIntake = new BallIntake();
  private final Shooter m_shooter = new Shooter();

  private final AHRS m_navx = new AHRS(SPI.Port.kMXP);

  private final TestAuto m_driveCommand = new TestAuto(m_driveTrain, m_navx);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_driveTrain.setDefaultCommand(new InstantCommand(m_driveTrain::stop));
    m_ballIntake.setDefaultCommand(new InstantCommand(m_ballIntake::neutral));
    m_shooter.setDefaultCommand(new InstantCommand(m_shooter::neutral));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    leftShooterBumper.whenHeld(new Intake(m_ballIntake, 2.0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_driveCommand;
    return m_driveCommand;
  }

  public Command getTeleopCommand() {
    return new Teleop(m_driveTrain, driverController);
  }
}