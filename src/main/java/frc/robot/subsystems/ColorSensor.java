/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WheelColors;

public class ColorSensor extends SubsystemBase {
  ColorSensorV3 _colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  ColorMatch _colorMatcher = new ColorMatch();

  /**
   * Creates a new ColorSensor.
   */
  public ColorSensor() {
    _colorMatcher.addColorMatch(WheelColors.BLUE);
    _colorMatcher.addColorMatch(WheelColors.GREEN);
    _colorMatcher.addColorMatch(WheelColors.RED);
    _colorMatcher.addColorMatch(WheelColors.YELLOW);
  }

  public Color getRawColor() {
    return _colorSensor.getColor();
  }

  public WheelColor getStripColor() {
    ColorMatchResult colorMatchResult = _colorMatcher.matchClosestColor(_colorSensor.getColor());

    if (colorMatchResult.color == WheelColors.BLUE) {
      return WheelColor.BLUE;
    } else if (colorMatchResult.color == WheelColors.GREEN) {
      return WheelColor.GREEN;
    } else if (colorMatchResult.color == WheelColors.RED) {
      return WheelColor.RED;
    } else if (colorMatchResult.color == WheelColors.YELLOW) {
      return WheelColor.YELLOW;
    } else {
      return WheelColor.UNKNOWN;
    }
  }

  enum WheelColor {
    BLUE, GREEN, RED, YELLOW, UNKNOWN
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
