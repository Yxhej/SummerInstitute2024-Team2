package frc.robot.shooter;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final CANSparkMax motor = new CANSparkMax(7, MotorType.kBrushless);
  private RelativeEncoder pivotEncoder;
  private PIDController motorController =
      new PIDController(ShooterConstants.kp1, ShooterConstants.ki1, ShooterConstants.kd1);

  public void periodic() {}

  public Command goTo(double speed) {
    return run(
        () -> {
          double motorSpeed = pivotEncoder.getVelocity();
          double v = motorController.calculate(motorSpeed, speed);
          motor.setVoltage(v);
        }).finallyDo(() -> motor.setVoltage(0));
  }

  public boolean atSetpoint() {
    return motorController.atSetpoint();
  }
}
