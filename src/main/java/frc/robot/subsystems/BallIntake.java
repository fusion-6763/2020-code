//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.INTAKE_ARM_PORT;
import static frc.robot.Constants.IntakeConstants.INTAKE_SUCC_PORT;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
  // The spark that operates the motor.
  private final Spark arm = new Spark(INTAKE_ARM_PORT);
  private final Spark succ = new Spark(INTAKE_SUCC_PORT);

  public BallIntake() {
    setDefaultCommand(new RunCommand(this::neutral, this));
  }

  // Inputs the ball a full speed backwards.
  public void inputBall() {
    succ.set(1);
  };

  public void armUp() {
    arm.set(1);
  }
  
  public void armDown() {
    arm.set(-1);
  }

  // Stops spinning the motor.
  public void neutral() {
    succ.set(0);
    arm.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}