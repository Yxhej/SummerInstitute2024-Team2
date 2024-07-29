// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Intake;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports.IntakePorts;
import static frc.robot.Intake.IntakeConstants.*;

  //intake roller
public class Intake extends SubsystemBase {

    private IntakeConstants intakeConstants = new IntakeConstants();
    private final CANSparkMax pivotMotor = new CANSparkMax(IntakePorts.pivot, MotorType.kBrushless);
    private final DigitalInput hopperBeamSensor = new DigitalInput(IntakePorts.intakeBeam);
    private final CANSparkMax rollerMotor = new CANSparkMax(IntakePorts.roller, MotorType.kBrushless);
    // RelativeEncoder encoder
    private AbsoluteEncoder pivotEncoder;
    private PIDController pivotController = new PIDController(kp1, ki1, kd1); //it gives the change in radians
    private PIDController rollerController = new PIDController(kp2, ki2, kd2);

    //autonomous
    





    //human
    public void setPosition() { 
        double motorDegrees = pivotEncoder.getPosition();
        double v = pivotController.calculate(motorDegrees, intakeConstants.targetDegrees.magnitude());
        pivotMotor.setVoltage(v);
    }


    public void setRoller(){
        while(!hopperBeamSensor.get()){
        // double motorSpeed = pivotEncoder.getVelocity();
        // double v = rollerController.calculate(motorSpeed, intakeConstants.targetSpeed.magnitude());
        // rollerMotor.setVoltage(v);
        // ^Not really necessary code?

          rollerMotor.setVoltage(0.5); //Adjust as needed.
        }


    }

    public void resetIntake(){
        double motorDegrees = pivotEncoder.getPosition();
        double v = pivotController.calculate(motorDegrees, 0); //change when testing with robot
        pivotMotor.setVoltage(v);
        rollerMotor.set(0);
    }

    @Override
    public void periodic() {
        setPosition();
    }   
}
