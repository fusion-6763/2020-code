/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class TimedShoot extends CommandBase {
  private Shooter _shooter;
  private double _time;

  private Timer _timer;

  /**
   * Creates a new DriveStraight.
   */
  public TimedShoot(final Shooter shooter, final double time) {
    _shooter = shooter;
    _time = time;

    _timer = new Timer();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _timer.start();
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   */
  @Override
  public void execute() {
    _shooter.outputBall();
    System.out.println("Timed Shoot...");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_timer.get() >= _time) {
      return true;
    } else {
      return false;
    }
  }
}
