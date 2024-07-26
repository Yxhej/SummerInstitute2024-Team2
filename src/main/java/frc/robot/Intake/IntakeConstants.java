package frc.robot.Intake;


import edu.wpi.first.units.*;
import static edu.wpi.first.units.Units.*;

public class IntakeConstants {
    public static final Measure<Angle> startDegrees = Degrees.of(0.0);
    Measure<Angle> targetDegrees = Degrees.of(60.0); //Adjust as 
    Measure<Velocity<Distance>> targetSpeed = MetersPerSecond.of (30); //Adjust as needed
    public static final double kp1 = 0;
    public static final double kd1 = 0;
    public static final double ki1 = 0;
    public static final double kp2 = 0;
    public static final double kd2 = 0;
    public static final double ki2 = 0;
}
//starts up
//goes down until position = end
//intake happens at a button
//after ball goes through the ir beam, then it will return to starting position
