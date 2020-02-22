/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automodes;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Aim;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.DriveStraight.Mode;
import frc.robot.commands.Intake;
import frc.robot.commands.LoadBall;
import frc.robot.commands.RunArm;
import frc.robot.commands.RunArm.ArmMode;
import frc.robot.commands.RunHopper;
import frc.robot.commands.Shoot;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SideAuto extends SequentialCommandGroup {

  /**
   * Creates a new SideAuto.
   */
  public SideAuto(final DriveTrain driveTrain, final Shooter shooter, final BallIntake ballIntake, final Hopper hopper,
      final Tower tower, final Turret turret, final Limelight limelight, final Arm arm) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new ParallelRaceGroup(
        new RunArm(arm, ArmMode.UP), new Intake(ballIntake)
      ),
      new ParallelDeadlineGroup(
        new DriveStraight(driveTrain, Mode.DISTANCE, 161.16, .8),
        new Aim(turret, limelight),
        new Intake(ballIntake)
      ),
      new ParallelCommandGroup(
        new Shoot(shooter).withTimeout(1),
        new Intake(ballIntake), new Aim(turret, limelight)
      ),
      new ParallelRaceGroup(
        new Shoot(shooter).withTimeout(3),
        new RunHopper(hopper),
        new LoadBall(tower),
        new Intake(ballIntake)
      ),
      new ParallelRaceGroup(
        new DriveStraight(driveTrain, Mode.DISTANCE, 157.311, .8),
        new Intake(ballIntake)
      ),
      new DriveStraight(driveTrain, Mode.DISTANCE, -157.311, -0.8),
      new ParallelCommandGroup(
        new Shoot(shooter).withTimeout(1),
        new Aim(turret, limelight)
      ),
      new ParallelRaceGroup(
        new Shoot(shooter).withTimeout(3),
        new RunHopper(hopper),
        new LoadBall(tower)
      )
    );
  }
}
