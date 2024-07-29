package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Ports.IntakePorts;

public class shooter extends SubsystemBase {
    private final CANSparkMax motor = new CANSparkMax(7,MotorType.kBrushless);
    private double power; 
    private final DigitalInput hopperBeamSensor = new DigitalInput (IntakePorts.intakeBeam);
    private final DigitalInput limit = new DigitalInput (5);

    public void periodic() {
        //hopper
        if (!hopperBeamSensor.get()) {
          turnOn();
        }
        else if (hopperBeamSensor.get()) {
          turnOff();
        }
      }
    
    public void turnOn(){
        motor.setVoltage();
    }

    public void turnOff(){
        motor.set(0);
    }

    public void Overshoot() {
        power -= 0.001;
    }

    public void UnderShoot() {
        power += 0.001;
    }

    public void Record() {
        System.out.println(power);
    }
;
}
