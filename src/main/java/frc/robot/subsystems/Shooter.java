package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.LIMELIGHT_X_RANGE;
import static frc.robot.Constants.ShooterConstants.MAX_NEO_RPM;
import static frc.robot.Constants.ShooterConstants.SHOOTER_PORT1;
import static frc.robot.Constants.ShooterConstants.SHOOTER_PORT2;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.Limelight;

public class Shooter extends SubsystemBase {

  // The spark that operates the motor.
  private final CANSparkMax motor1 = new CANSparkMax(SHOOTER_PORT1, MotorType.kBrushless);
  private final CANSparkMax motor2 = new CANSparkMax(SHOOTER_PORT2, MotorType.kBrushless);

  private final Limelight _limelight;

  public Shooter(final Limelight limelight) {
    _limelight = limelight;
    setDefaultCommand(new RunCommand(this::neutral, this));
  }

  // Launches that ball at full speed forwards.
  public void outputBall() {
    /*final double rpm = _limelight.getRPM();
    final double motorSpeed;
    if (rpm > MAX_NEO_RPM) {
      motorSpeed = 1;
    } else {
      motorSpeed = rpm / MAX_NEO_RPM;
    }*/
    double motorSpeed = 1;

    motor1.set(-motorSpeed);
    motor2.set(motorSpeed);
  };

  public void speed(final double motorSpeed) {
    System.out.println("Yes");
    motor1.set(-motorSpeed);
    motor2.set(motorSpeed);
  }
  // Stops spinning the motor.
  public void neutral() {
    motor1.set(0);
    motor2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getX() {
    return _limelight.getX();
  }

  public double getRPM() {
    return _limelight.getRPM();
  }
}