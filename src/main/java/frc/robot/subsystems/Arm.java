//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.INTAKE_ARM_SOLENOID_PORT_L;
import static frc.robot.Constants.IntakeConstants.INTAKE_ARM_SOLENOID_PORT_R;

import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  // The spark that operates the motor.
  private final Solenoid arm1 = new Solenoid(INTAKE_ARM_SOLENOID_PORT_L);
  private final Solenoid arm2 = new Solenoid(INTAKE_ARM_SOLENOID_PORT_R);

  public Arm(){
  }

  public void armUp() {
    arm1.set(false);
    arm2.set(false);
  }

  public void armDown() {
    arm1.set(true);
    arm2.set(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}