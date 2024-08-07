package frc.robot.subsystems.drive;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class Drive extends SubsystemBase {
  private DifferentialDrive robotDrive;

  private final CANSparkMax leftLeader =
      new CANSparkMax(Ports.kleftMotor1Port, MotorType.kBrushless);
  private final CANSparkMax leftfollower =
      new CANSparkMax(Ports.kleftMotor2Port, MotorType.kBrushless);

  private final CANSparkMax rightLeader =
      new CANSparkMax(Ports.krightMotor1Port, MotorType.kBrushless);
  private final CANSparkMax rightfollower =
      new CANSparkMax(Ports.krightMotor2Port, MotorType.kBrushless);

  public Drive() {
    robotDrive = new DifferentialDrive(leftLeader::set, rightLeader::set);

    leftLeader.restoreFactoryDefaults();
    leftfollower.restoreFactoryDefaults();
    rightLeader.restoreFactoryDefaults();
    rightfollower.restoreFactoryDefaults();

    leftLeader.setInverted(true);
    rightfollower.follow(rightLeader);
    leftfollower.follow(leftLeader);
  }

  public void drive(double leftSpeed, double rightSpeed) {
    leftLeader.set(Math.copySign(leftSpeed * leftSpeed, leftSpeed));
    rightLeader.set(Math.copySign(rightSpeed * rightSpeed, rightSpeed));
  }

  public void arcadeDrive(double forward, double rotation) {
    robotDrive.arcadeDrive(forward, rotation);
  }
}
