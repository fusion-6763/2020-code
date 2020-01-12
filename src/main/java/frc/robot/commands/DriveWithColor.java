/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithColor extends CommandBase {
  private ColorMatch colorMatcher;

  ColorSensorV3 _colorSensor;
  DriveTrain _driveTrain;

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  /**
   * Creates a new DriveWithColor.
   */
  public DriveWithColor(DriveTrain driveTrain, ColorSensorV3 colorSensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);

    _colorSensor = colorSensor;
    _driveTrain = driveTrain;

    colorMatcher = new ColorMatch();
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ColorMatchResult result = colorMatcher.matchClosestColor(_colorSensor.getColor());
    if (result.color == kYellowTarget) {
      _driveTrain.drive(0.0, -0.6);
    } else if (result.color == kRedTarget) {
      _driveTrain.drive(0.0, 0.6);
    } else {
      _driveTrain.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
