/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.TURRET_END;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.Limelight.LightMode;
import frc.robot.subsystems.Turret;

public class Aim extends SequentialCommandGroup {
  private Limelight _limelight;

  public Aim(Turret turret, Limelight limelight, boolean infinite) {
    super(
      new InstantCommand(() -> limelight.setLights(LightMode.ON)),
      new WaitCommand(0.3),
      infinite ? new RawAim(turret, limelight).perpetually() : new RawAim(turret, limelight)
    );

    _limelight = limelight;
  }

  public void end(boolean interrupted) {
    _limelight.setLights(LightMode.DEFAULT);

    super.end(interrupted);
  }

  static private class RawAim extends CommandBase {
    private final Turret _turret;
    private final Limelight _limelight;
    private int ticksLockedOn = 0;
    private int TICKS_AIMED_DESIRED = 17;

    /**
     * Creates a new Aim.
     */
    public RawAim(final Turret turret, final Limelight limelight) {
      _turret = turret;
      _limelight = limelight;

      addRequirements(_turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      _limelight.setLights(LightMode.ON);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      double turretSpeed = 0;
      _limelight.setDriverMode(false);

      turretSpeed = _limelight.getX() * 0.005;
      _turret.set(-turretSpeed);

      if (_limelight.getX() < -.5) {
        ticksLockedOn = 0;
      } else if (_limelight.getX() > .5) {
        ticksLockedOn = 0;
      } else {
        ticksLockedOn++;
      }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      _turret.stop();

      _limelight.setLights(LightMode.DEFAULT);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      // TODO: Use TURRET_END
      /*
       * if (_turret.getEncoder() <= 0 && _limelight.getX() > 0) {
       * System.out.println("Too far right");
       * 
       * return true; } else if (_turret.getEncoder() >= TURRET_END &&
       * _limelight.getX() < 0) { System.out.println("Too far left");
       * 
       * return true; } else if (_limelight.getX() > -2 && _limelight.getX() < 2) {
       * return true; } else { return false; } }
       */
      //System.out.println(_limelight.getX());
      //System.out.println(ticksLockedOn);

      if (ticksLockedOn >= TICKS_AIMED_DESIRED) {
        return true;
      } else {
        return false;
      }
    }
  }
}