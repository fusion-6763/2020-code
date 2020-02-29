//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.INTAKE_ARM_PORT;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  // The spark that operates the motor.
  private final CANSparkMax arm = new CANSparkMax(INTAKE_ARM_PORT, MotorType.kBrushless);
  private final CANEncoder armEncoder = arm.getEncoder();

  public void armUp() {
    arm.set(-0.3);
  }

  public void armDown() {
    arm.set(0.3);
  }

  public void set(double value){
    arm.set(value);
  }

  public double getEncoderPosition(){
    return armEncoder.getPosition();
  }

  public void resetEncoder(){
    armEncoder.setPosition(0);
  }

  // Stops spinning the motor.
  public void neutral() {
    arm.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}