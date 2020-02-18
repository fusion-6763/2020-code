/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.ENCODER_DISTANCE_TO_90DEG;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallIntake;

public class RunArm extends CommandBase {
  private BallIntake _ballIntake;
  private ArmMode _mode;

  /**
   * Creates a new ArmUp.
   */
  public RunArm(final BallIntake ballIntake, final ArmMode mode) {
    _ballIntake = ballIntake;
    _mode = mode;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (_mode == ArmMode.DOWN) {
      _ballIntake.armDown();
    } else {
      _ballIntake.armUp();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_mode == ArmMode.DOWN && _ballIntake.getEncoderPosition() >= ENCODER_DISTANCE_TO_90DEG) {
      return true;
    } else if(_mode == ArmMode.UP && _ballIntake.getEncoderPosition() >= 0.0){
      return true;
    } else {
      return false;
    }
  }

  public enum ArmMode {
    UP, DOWN
  }
}
