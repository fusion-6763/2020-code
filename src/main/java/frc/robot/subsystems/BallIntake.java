//Operates the robot motors that control the ball-shooting mechanism. 
package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.INTAKE_SUCC_PORT;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
  // The spark that operates the motor.
  private final CANSparkMax succ = new CANSparkMax(INTAKE_SUCC_PORT, MotorType.kBrushless);  public BallIntake() {
    setDefaultCommand(new RunCommand(this::neutral, this));
  }

  // Inputs the ball a full speed backwards.
  public void inputBall() {
    succ.set(-0.9);
  };

  public void outputBall(){
    succ.set(0.9);
  }

  // Stops spinning the motor.
  public void neutral() {
    succ.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}