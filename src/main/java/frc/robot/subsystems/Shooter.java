package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.LIMELIGHT_X_RANGE;
import static frc.robot.Constants.ShooterConstants.MAX_NEO_RPM;
import static frc.robot.Constants.ShooterConstants.SHOOTER_PORT1;
import static frc.robot.Constants.ShooterConstants.SHOOTER_PORT2;

import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.Limelight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends SubsystemBase {

  // The spark that operates the motor.
  private final CANSparkMax motor1 = new CANSparkMax(SHOOTER_PORT1, MotorType.kBrushless);
  private final CANSparkMax motor2 = new CANSparkMax(SHOOTER_PORT2, MotorType.kBrushless);
  
  private final CANEncoder motor1Encoder = motor1.getEncoder();
  private final CANEncoder motor2Encoder = motor2.getEncoder();

  private double currentMotorSpeed = 0.0;
  private final double differenceFactor = 0.05;

  private final Limelight _limelight;

  public Shooter(final Limelight limelight) {
    _limelight = limelight;
    setDefaultCommand(
      new RunCommand(() -> {
        neutral();
        //System.out.println("Shooter Default");
      }, this)
    );
    SmartDashboard.putNumber("kF", 1.0/5400.0);
    SmartDashboard.putNumber("kP", 1e-3);
    SmartDashboard.putNumber("Motor Speed", 0);
    SmartDashboard.putNumber("Set Motor Speed", 0);
  }

  // Launches that ball at full speed forwards.
  public void outputBall() {
    final double targetRPM = _limelight.getRPM();
    //final double targetRPM = SmartDashboard.getNumber("Target Velocity", 2000);
    final double currentRPM = -motor1Encoder.getVelocity();
    //System.out.println("RPM: ");
    //System.out.print(motor1Encoder.getVelocity());
    //System.out.println(rpm);

    //SmartDashboard.putNumber("Target Velocity", targetRPM);
    SmartDashboard.putNumber("Actual Velocity", currentRPM);

    //SmartDashboard.putNumber("kF", 1.0/5400.0);
    //SmartDashboard.putNumber("kP", 1e-3);

    double kF = SmartDashboard.getNumber("kF", 1.0/5400.0);
    double kP = SmartDashboard.getNumber("kP", 1e-3);
    double error = targetRPM - currentRPM;

    
    //double motorSpeed = kF * targetRPM + kP * error;
    //double motorSpeed = SmartDashboard.getNumber("Set Motor Speed", 0.7);
    double motorSpeed = 0.7; 

    // if (targetRPM > MAX_NEO_RPM) {
    //   motorSpeed = 1;
    // } 
    // else {
    //   //motorSpeed = rpm / MAX_NEO_RPM;
    //   if (Math.abs(currentRPM - targetRPM) < 100) {
    //     //Just right
    //   }
    //   else if (currentRPM < targetRPM) {
    //     System.out.println("Too low");
    //     currentMotorSpeed += differenceFactor;
    //   }
    //   else if (currentRPM > targetRPM) {
    //     System.out.println("Too high");
    //     currentMotorSpeed -= differenceFactor;
    //   }        
    //   motorSpeed = currentMotorSpeed;
    // }

    //SmartDashboard.putNumber("Motor Speed", motorSpeed);
    //motorSpeed = 0.7;
    System.out.println(motorSpeed);
    if (motorSpeed > 0) {
      motor1.set(-motorSpeed);
      motor2.set(motorSpeed);
    }
  };

  public void speed(final double motorSpeed) {
    //System.out.println("Yes");
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
    return 0.0;
  }
}