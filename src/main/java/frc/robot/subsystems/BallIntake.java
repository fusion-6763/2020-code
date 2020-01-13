//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.DriveConstants.SHOOTER_PORT;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
  // The spark that operates the motor.
  private final Spark motor = new Spark(SHOOTER_PORT);

  // Launches that ball at full speed forwards.
  public void OutputBall() {
    motor.set(1);
  };

  // Inputs the ball a full speed backwards.
  public void InputBall() {
    motor.set(-1);
  };

  // Stops spinning the motor.
  public void Neutral() {
    motor.set(0);
  }
}