//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;

public class Shooter extends SubsystemBase{
  //The spark that operates the motor.
  private final Spark motor = new Spark(1);

  //Launches that ball at full speed forwards.
  public void OutputBall(){
    motor.set(1);
  };

  //Inputs the ball a full speed backwards.
  public void InputBall(){
    motor.set(-1);
  };
  
  //Stops spinning the motor.
  public void Neutral(){
    motor.set(0);
  }
  /*@Override
  public void periodic(){
    //Code in here will run over and over and over and over and over and over again.
  }*/
}