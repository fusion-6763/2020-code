//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.DriveConstants.SHOOTER_PORT;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
  // The spark that operates the motor.
  private final Spark motor = new Spark(SHOOTER_PORT);
  // Inputs the ball a full speed backwards.
  public void inputBall() {
    motor.set(-1);
  };

  // Stops spinning the motor.
  public void neutral() {
    motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}