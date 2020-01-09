/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.automodes;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveForward;
import frc.robot.subsystems.DriveTrain;

import static frc.robot.Constants.DriveConstants.ENCODER_COUNTS_PER_ROTATION;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TestAuto extends SequentialCommandGroup {
  /**
   * Creates a new TestAuto.
   */
  public TestAuto(DriveTrain driveTrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new DriveForward(driveTrain, ENCODER_COUNTS_PER_ROTATION * 10, 0.6));
  }
}
