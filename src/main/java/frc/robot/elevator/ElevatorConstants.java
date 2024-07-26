package frc.robot.elevator;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity;

public class ElevatorConstants {    
    public static final Measure<Velocity<Distance>> targetSpeed = MetersPerSecond.of (30); //Adjust as needed
    public static final double kp3 = 0;
    public static final double kd3 = 0;
    public static final double ki3 = 0;
    public static final double OPERATING_VOLTS = 5;
    public static final int hopperMotorPort = 0; //Need to change as soon as we can test code on robot
    public static final int beamBreakPort = 0; //Need to change as soon as we can test code on robot

}

