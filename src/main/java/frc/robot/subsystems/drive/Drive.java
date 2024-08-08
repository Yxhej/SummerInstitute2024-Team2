package frc.robot.subsystems.drive;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import static frc.robot.subsystems.drive.DriveConstants.*;
public class Drive extends SubsystemBase {
  private final DifferentialDrive robotDrive;

  private final CANSparkMax leftLeader =
      new CANSparkMax(Ports.kleftMotor1Port, MotorType.kBrushless);
  private final CANSparkMax leftfollower =
      new CANSparkMax(Ports.kleftMotor2Port, MotorType.kBrushless);

      AbsoluteEncoder Leftencoder;

  private final CANSparkMax rightLeader =
      new CANSparkMax(Ports.krightMotor1Port, MotorType.kBrushless);
  private final CANSparkMax rightfollower =
      new CANSparkMax(Ports.krightMotor2Port, MotorType.kBrushless);
      
      AbsoluteEncoder Rightencoder;
      
      private final PIDController PIDcontrol = new PIDController(kp1, ki1, kd1);
      private final SimpleMotorFeedforward feeding = new SimpleMotorFeedforward(ks, kv, ka );

  public Drive() {
    robotDrive = new DifferentialDrive(leftLeader::set, rightLeader::set);

    leftLeader.restoreFactoryDefaults();
    leftfollower.restoreFactoryDefaults();
    rightLeader.restoreFactoryDefaults();
    rightfollower.restoreFactoryDefaults();

    leftLeader.setInverted(true);
    rightfollower.follow(rightLeader);
    leftfollower.follow(leftLeader);

    Leftencoder = leftLeader.getAbsoluteEncoder();
    Rightencoder = leftLeader.getAbsoluteEncoder();
  }

  public void drive(double leftSpeed, double rightSpeed) {
    leftLeader.set(Math.copySign(leftSpeed * leftSpeed, leftSpeed));
    rightLeader.set(Math.copySign(rightSpeed * rightSpeed, rightSpeed));
  }

  public void arcadeDrive(double forward, double rotation) {
    robotDrive.arcadeDrive(forward, rotation);
  }
  public Command drivePID(double leftSpeed, double rightSpeed ) {
    return run(() -> {
      leftLeader.setVoltage(PIDcontrol.calculate(Leftencoder.getVelocity(), leftSpeed)+ feeding.calculate(leftSpeed));
       rightLeader.setVoltage(PIDcontrol.calculate(Rightencoder.getVelocity(), rightSpeed)+ feeding.calculate(rightSpeed));
    });
  }
}