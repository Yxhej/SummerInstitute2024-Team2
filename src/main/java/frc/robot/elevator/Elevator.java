package frc.robot.elevator;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.elevator.ElevatorConstants.*;

import com.revrobotics.CANSparkMax;


public class Elevator extends SubsystemBase {
    private final CANSparkMax motor;
    private final DigitalInput beamBreak;
    
    public Elevator() {
        motor = new CANSparkMax(Ports.IntakePorts.hopperMotorPort, MotorType.kBrushless);
        beamBreak = new DigitalInput(Ports.IntakePorts.beamBreakPort);
    }

    public void set(double v) {
        motor.set(v);
    }

    public boolean brokenBeam() {
        return !beamBreak.get();
    }

    public Command stopElevator(){
        return runHopper(0);
    }
    
    public Command runHopper(double v) {
        return runOnce(() -> set(v))
        .andThen(Commands.idle(this))
        .finallyDo(() -> set(0));
    } 

    public Command forward() { 
        return runHopper(elevatorSpeedOutput).until(() -> brokenBeam());
    }

    public Command backward(){
        return runHopper(-1 * elevatorSpeedOutput).until(() -> brokenBeam());
    }

    public Command elevatorIntake(){
        return forward().until(() -> brokenBeam());
    }
}
