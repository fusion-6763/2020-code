/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automodes;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Aim;
import frc.robot.commands.ArmDown;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.DriveStraight.Mode;
import frc.robot.commands.LoadBall;
import frc.robot.commands.RunHopper;
import frc.robot.commands.Shoot;
import frc.robot.commands.TurretStraight;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.Limelight.LightMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SimpleAuto extends SequentialCommandGroup {

  /**
   * Creates a new TestAuto.
   */
  public SimpleAuto(final Arm arm, final DriveTrain driveTrain, final Shooter shooter, final Hopper hopper, final Tower tower, final Limelight limelight, final Turret turret) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new TurretStraight(turret),
      new SequentialCommandGroup(
        new InstantCommand(() -> limelight.setLights(LightMode.ON)),
        new WaitCommand(0.3),
        new Aim(turret, limelight),
        new InstantCommand(() -> limelight.setLights(LightMode.DEFAULT))
      ),
      new Shoot(shooter).withTimeout(0.5),
      new ParallelCommandGroup(
        new RunHopper(hopper),
        new LoadBall(tower),
        new Shoot(shooter)
      ).withTimeout(5),
      new DriveStraight(driveTrain, Mode.TIME, 5, -0.3)
    );
  }
}
