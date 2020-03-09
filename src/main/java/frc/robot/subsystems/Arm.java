//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.INTAKE_ARM_SOLENOID_PORT_IN;
import static frc.robot.Constants.IntakeConstants.INTAKE_ARM_SOLENOID_PORT_OUT;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  // The spark that operates the motor.
  private final DoubleSolenoid arm = new DoubleSolenoid(INTAKE_ARM_SOLENOID_PORT_OUT, INTAKE_ARM_SOLENOID_PORT_IN);

  public Arm() {
  }

  public void armUp() {
    arm.set(Value.kReverse);
  }

  public void armDown() {
    arm.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}