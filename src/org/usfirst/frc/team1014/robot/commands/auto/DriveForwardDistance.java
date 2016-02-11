package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Tze Hei T.
 *
 */
public class DriveForwardDistance extends CommandBase {

	public double speed; // creates a variable for the driving speed
	public double distance; // creates a variable for the driving distance
	public double ultraDistance; // creates a variable for ???

	/**
	 * Constructor
	 * 
	 * @param speed
	 *            - speed the robot will run at
	 * @param distance
	 *            - the distance from something it will stop at
	 */
	public DriveForwardDistance(double speed, double distance) {
		this.distance = distance; // sets this class' distance variable equal to the value entered
		this.speed = speed; // same with speed
		requires((Subsystem) driveTrain); // requests exclusive use of the driveTrain subsystem
	}
	

	@Override
	protected void initialize() {
		driveTrain.tankDrive(0, 0);
	}

	@Override
	public String getConsoleIdentity() {
		return "DriveForwardDistance";
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(0, 0);
	}

	@Override
	protected void execute() {
		ultraDistance = driveTrain.getUltraDistance(true); // Gets the ultrasonic distance in inches
		driveTrain.tankDrive(speed, speed); // makes the robot move forward at a speed equal to the value entered
	}

	@Override
	protected void interrupted() {
		System.out.println("DriveForwardDistance was interrupted");
	}

	@Override
	protected boolean isFinished() {
		if (ultraDistance < distance) {
			return true;
		} else {
			return false;
		}
	}

}
