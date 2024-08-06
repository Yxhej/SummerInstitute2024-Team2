package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Ports.IntakePorts;

public class Shooter extends SubsystemBase {
    private final CANSparkMax motor = new CANSparkMax(7,MotorType.kBrushless);
    private final DigitalInput hopperBeamSensor = new DigitalInput (IntakePorts.intakeBeam);
    private RelativeEncoder pivotEncoder; 
    double kp1 = 1;
    double ki1 = 1;
    double kd1 = 1;
    private PIDController motorController = new PIDController(kp1, ki1, kd1);

    public void periodic() {
        //hopper
        if (!hopperBeamSensor.get()) {
          goTo(4);
        }
        else if (hopperBeamSensor.get()) {
          turnOff();
        }
      }

    public boolean BeamBreak() {
        return hopperBeamSensor.get();
    }
    
    public Command goTo(double speed){
      return run(() -> {
          double motorSpeed = pivotEncoder.getVelocity();
          double v = motorController.calculate(motorSpeed, speed);
          motor.setVoltage(v);
      })
      .until(() -> motorController.atSetpoint());
    }

    public Command turnOff(){
        return runOnce(() -> motor.set(0));
    }

}
