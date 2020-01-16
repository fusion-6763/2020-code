package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallIntake;

public class Intake extends CommandBase {
  private BallIntake _ballIntake;

  public Intake(final BallIntake ballIntake) {
    _ballIntake = ballIntake;

    addRequirements(ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    _ballIntake.inputBall();
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