package frc.robot.subsystems.DriveSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase{  
  
    private final CANSparkMax leftLeader = new CANSparkMax(DriveConstants.kleftMotor1Port, MotorType.kBrushless);
    private final CANSparkMax leftfollower = new CANSparkMax(DriveConstants.kleftMotor2Port, MotorType.kBrushless);
    
    private final CANSparkMax rightLeader = new CANSparkMax(DriveConstants.krightMotor1Port, MotorType.kBrushless);
    private final CANSparkMax rightfollower = new CANSparkMax(DriveConstants.krightMotor2Port, MotorType.kBrushless);
     
    public Drive() {
        leftLeader.restoreFactoryDefaults();
        leftfollower.restoreFactoryDefaults();
        rightLeader.restoreFactoryDefaults();
        rightfollower.restoreFactoryDefaults();
        
        leftLeader.setInverted(true);
        rightfollower.follow(rightLeader);
        leftfollower.follow(leftLeader);
    }

    public void drive(double leftSpeed, double rightSpeed){
        leftLeader.set(Math.copySign(leftSpeed * leftSpeed, leftSpeed));
        rightLeader.set(Math.copySign(rightSpeed * rightSpeed, rightSpeed));
    }
}