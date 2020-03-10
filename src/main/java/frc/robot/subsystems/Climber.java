//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.CLIMBER_SOLENOID_PORT_OUT;
import static frc.robot.Constants.ClimberConstants.CLIMBER_SOLENOID_PORT_IN;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  // The spark that operates the motor.
  //TODO
  //private final DoubleSolenoid piston = new DoubleSolenoid(CLIMBER_SOLENOID_PORT_OUT, CLIMBER_SOLENOID_PORT_IN);

  public Climber(){
  }

  public void up() {
    //piston.set(Value.kForward);
  }

  public void down() {
    //piston.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}