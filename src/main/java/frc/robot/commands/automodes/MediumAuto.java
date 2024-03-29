/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automodes;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Aim;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.DriveStraight.Mode;
import frc.robot.commands.LoadBall;
import frc.robot.commands.Shoot;
import frc.robot.commands.StopShooting;
import frc.robot.commands.TurretStraight;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.BallLoader;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class MediumAuto extends SequentialCommandGroup {

  /**
   * Creates a new TestAuto.
   */
  public MediumAuto(final Arm arm, final DriveTrain driveTrain, final Shooter shooter, final BallLoader ballLoader, final Limelight limelight, final Turret turret) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new DriveStraight(driveTrain, Mode.TIME, 1, -0.5),
      new DriveStraight(driveTrain, Mode.TIME, 1, 0.0),
      new ParallelCommandGroup(
        new DriveStraight(driveTrain, Mode.TIME, 2, 0.4),
        new Shoot(shooter).withTimeout(2)
        //new TurretStraight(turret).withTimeout(2)
      ),
       new ParallelCommandGroup(
         new LoadBall(ballLoader).withTimeout(5),
         new Shoot(shooter).withTimeout(5)
       )
    );
  }
}
