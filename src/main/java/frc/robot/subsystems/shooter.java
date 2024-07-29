package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Ports.IntakePorts;

public class shooter extends SubsystemBase {
    private final CANSparkMax motor = new CANSparkMax(7,MotorType.kBrushless);
    private double power; 
    private final DigitalInput hopperBeamSensor = new DigitalInput (IntakePorts.intakeBeam);
    private RelativeEncoder pivotEncoder; 
    private PIDController motorController = new PIDController (kp1, ki1, kd1);

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
        double motorSpeed = pivotEncoder.getVelocity();
        double v = motorController.calculate(motorSpeed, );
        motor.setVoltage(v);
    }

    public void turnOff(){
        motor.set(0);
    }
}
