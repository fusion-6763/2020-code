package frc.robot.commands;

import frc.robot.subsystems.BallIntake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class Intake extends CommandBase {
  double _time;
  Timer _timer;
  BallIntake _ballIntake = new BallIntake();

  public void intake(final Double time) {
    _time = time;
    _timer = new Timer();
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
    _ballIntake.neutral();
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