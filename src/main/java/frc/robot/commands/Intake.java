package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallIntake;

public class Intake extends CommandBase {
  double _time;
  Timer _timer;
  BallIntake _ballIntake;

  public Intake(final BallIntake ballIntake, final Double time) {
    _time = time;
    _timer = new Timer();
    _ballIntake = ballIntake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _timer.start();
  }

  @Override
  public void execute() {
    _ballIntake.inputBall();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Not required because we have a default command
    // _ballIntake.neutral();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_timer.get() > _time) {
      return true;
    } else {
      return false;
    }
  }
}