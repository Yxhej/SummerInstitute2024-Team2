package frc.robot.elevator;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.elevator.ElevatorConstants.*;

import com.revrobotics.CANSparkMax;


public class Elevator extends SubsystemBase {
    private final CANSparkMax motor;
    private final DigitalInput beamBreak;
    
    public Elevator() {
        motor = new CANSparkMax(hopperMotorPort, MotorType.kBrushless);
        beamBreak = new DigitalInput(beamBreakPort);
    }

    public void setVoltage(double v) {
        motor.setVoltage(v);
    }

    public double getVoltage() {
        return motor.getBusVoltage();
    }

    public boolean brokenBeam() {
        return beamBreak.equals(true);
    }
    
    public Command runHopper(double v) {
        return runOnce(() -> setVoltage(v));
    } 


    public Command forward() { 
        return runHopper(OPERATING_VOLTS).until(() -> brokenBeam()).andThen(runHopper(0));
        
    }
    

    // public Command forward() {
    //     hopper.setVoltage(OPERATING_VOLTS);
    //     if (hopper.brokenBeam()) {
    //         hopper.setVoltage(0); 
    //     }
    //     return
    // }


    public Command backward(){
        return runHopper(-1 * OPERATING_VOLTS).until(() -> brokenBeam()).andThen(runHopper(0));
    }
}
