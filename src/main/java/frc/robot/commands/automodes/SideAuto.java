/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automodes;

import static frc.robot.Constants.DriveConstants.ENCODER_DISTANCE_PER_PULSE;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Tower;

import frc.robot.commands.Aim;
import frc.robot.commands.ArmDown;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.Intake;
import frc.robot.commands.RunHopper;
import frc.robot.commands.RunTower;
import frc.robot.commands.Shoot;
import frc.robot.commands.TimedShoot;
import frc.robot.commands.DriveStraight.Mode;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SideAuto extends SequentialCommandGroup {

  /**
   * Creates a new SideAuto.
   */
  public SideAuto(final DriveTrain driveTrain, final Shooter shooter, final BallIntake ballIntake, final Hopper hopper,
      final Tower tower) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ParallelRaceGroup(new ArmDown(ballIntake), new Intake(ballIntake)),
        new ParallelDeadlineGroup(new DriveStraight(driveTrain, Mode.DISTANCE, 161.16 / ENCODER_DISTANCE_PER_PULSE, .8),
            new Aim(shooter), new Intake(ballIntake)),
        new ParallelCommandGroup(new TimedShoot(shooter, 1), new Intake(ballIntake), new Aim(shooter)),
        new ParallelRaceGroup(new TimedShoot(shooter, 3), new RunHopper(hopper), new RunTower(tower),
            new Intake(ballIntake)),
        new ParallelRaceGroup(new DriveStraight(driveTrain, Mode.DISTANCE, 157.311 / ENCODER_DISTANCE_PER_PULSE, .8),
            new Intake(ballIntake)),
        new DriveStraight(driveTrain, Mode.DISTANCE, -157.311 / ENCODER_DISTANCE_PER_PULSE, -0.8), // TODO: Fix
                                                                                                   // DriveStraight to
                                                                                                   // run backwards
        new ParallelCommandGroup(new TimedShoot(shooter, 1), new Aim(shooter)),
        new ParallelRaceGroup(new TimedShoot(shooter, 3), new RunHopper(hopper), new RunTower(tower))

    );
  }
}
