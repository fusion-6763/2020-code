/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.automodes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

import frc.robot.commands.DriveStraight;
import frc.robot.commands.RunHopper;
import frc.robot.commands.RunTower;
import frc.robot.commands.TimedShoot;
import frc.robot.commands.DriveStraight.Mode;
import frc.robot.commands.Aim;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Tower;
import static frc.robot.Constants.DriveConstants.ENCODER_DISTANCE_PER_PULSE;;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SimpleAuto extends SequentialCommandGroup {

  /**
   * Creates a new TestAuto.
   */
  public SimpleAuto(final DriveTrain driveTrain, final Shooter shooter, final Hopper hopper, final Tower tower) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new Aim(shooter), new TimedShoot(shooter, 1.5),
        new ParallelRaceGroup(new TimedShoot(shooter, 11.5), new RunHopper(hopper), new RunTower(tower)),
        new DriveStraight(driveTrain, Mode.DISTANCE, 12 / ENCODER_DISTANCE_PER_PULSE, 0.6)
    );
  }
}
