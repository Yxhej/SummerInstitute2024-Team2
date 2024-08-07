// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Intake;

import static edu.wpi.first.units.Units.Degrees;
import static frc.robot.Intake.IntakeConstants.*;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports.IntakePorts;

// intake roller
public class Intake extends SubsystemBase {

  private final CANSparkMax pivotMotor = new CANSparkMax(IntakePorts.pivot, MotorType.kBrushless);
  private final CANSparkMax rollerMotor = new CANSparkMax(IntakePorts.roller, MotorType.kBrushless);

  private AbsoluteEncoder pivotEncoder;
  private PIDController pivotController =
      new PIDController(kp1, ki1, kd1); // it gives the change in radians

  // human
  public Command pivotDown() {
    return run(() -> {
          double motorDegrees = pivotEncoder.getPosition();
          double v = pivotController.calculate(motorDegrees, TARGET_DEGREES.in(Degrees));
          pivotMotor.setVoltage(v);
        })
        .until(() -> pivotController.atSetpoint());
  }

  public Command runRoller() {
    // double motorSpeed = pivotEncoder.getVelocity();
    // double v = rollerController.calculate(motorSpeed, intakeConstants.targetSpeed.magnitude());
    // rollerMotor.setVoltage(v);
    // ^Not  necessary code? But Im leaving here in case its better.

    return runOnce(() -> rollerMotor.set(intakeOutputSpeed))
        .andThen(Commands.idle(this))
        .finallyDo(() -> rollerMotor.set(0)); // Adjust as needed.
  }

  public Command resetIntake() {
    return run(() -> {
          double motorDegrees = pivotEncoder.getPosition();
          double out = pivotController.calculate(motorDegrees, 0); // change when testing with robot
          pivotMotor.setVoltage(out);
          rollerMotor.set(0);
        })
        .until(() -> pivotController.atSetpoint());
  }
}
