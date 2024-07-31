package frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleConsumer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase{  
    private DifferentialDrive robotDrive;
  
    private final CANSparkMax leftLeader = new CANSparkMax(DriveConstants.kleftMotor1Port, MotorType.kBrushless);
    private final CANSparkMax leftfollower = new CANSparkMax(DriveConstants.kleftMotor2Port, MotorType.kBrushless);
    
    private final CANSparkMax rightLeader = new CANSparkMax(DriveConstants.krightMotor1Port, MotorType.kBrushless);
    private final CANSparkMax rightfollower = new CANSparkMax(DriveConstants.krightMotor2Port, MotorType.kBrushless);
     
     
    
    public Drive() {
         robotDrive = new DifferentialDrive(leftLeader::set, rightLeader::set );
        
        leftLeader.restoreFactoryDefaults();
        leftfollower.restoreFactoryDefaults();
        rightLeader.restoreFactoryDefaults();
        rightfollower.restoreFactoryDefaults();
        
        leftLeader.setInverted(true);
        rightfollower.follow(rightLeader);
        leftfollower.follow(leftLeader);
    }

    
    public void drive(double leftSpeed, double rightSpeed){
     leftLeader.set(leftSpeed);
     rightLeader.set(rightSpeed);
    }

    public void arcadeDrive(double forward, double rotation) {
        robotDrive.arcadeDrive(forward, rotation);
    }
}