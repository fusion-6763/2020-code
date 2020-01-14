package frc.robot.commands;

import frc.robot.subsystems.BallIntake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class Intake extends CommandBase {
  Timer timer;
  BallIntake ballIntake = new BallIntake();

  public void intake(final Double time) {
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  @Override
  public void execute() {
    ballIntake.InputBall();
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