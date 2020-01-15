//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.DriveConstants.INTAKE_PORT1;
import static frc.robot.Constants.DriveConstants.INTAKE_PORT2;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
  // The spark that operates the motor.
  private final Spark motor1 = new Spark(INTAKE_PORT1);
  private final Spark motor2 = new Spark(INTAKE_PORT2);

  // Inputs the ball a full speed backwards.
  public void inputBall() {
    motor1.set(-1);
  };

  // Stops spinning the motor.
  public void neutral() {
    motor1.set(0);
    motor2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}