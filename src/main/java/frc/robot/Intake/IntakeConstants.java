package frc.robot.Intake;


import edu.wpi.first.units.*;
import static edu.wpi.first.units.Units.*;

public class IntakeConstants {
    public static final Measure<Angle> START_DEGREES = Degrees.of(0.0);
    public static final Measure<Angle> TARGET_DEGREES = Degrees.of(60.0); //Adjust as 
    public static final Measure<Velocity<Distance>> TARGET_SPEED = MetersPerSecond.of (30); //Adjust as needed
    public static final double kp1 = 0;
    public static final double ki1 = 0;
    public static final double kd1 = 0;
    public static final double intakeOutputSpeed = 0.5;

}
//starts up
//goes down until position = end
//intake happens at a button
//after ball goes through the ir beam, then it will return to starting position
