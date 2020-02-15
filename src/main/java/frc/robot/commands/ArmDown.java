/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallIntake;
import static frc.robot.Constants.IntakeConstants.ENCODER_DISTANCE_TO_90DEG;

public class ArmDown extends CommandBase {
  private BallIntake _ballIntake;

  /**
   * Creates a new ArmUp.
   */
  public ArmDown(final BallIntake ballIntake) {
    _ballIntake = ballIntake;
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
    _ballIntake.armDown();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_ballIntake.getEncoderPosition() >= ENCODER_DISTANCE_TO_90DEG) {
      return true;
    } else {
      return false;
    }
  }
}