package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Sam G.
 *
 */
public class DriveForward extends CommandBase {
	
	public double driveTime; //creates a variable for the driving time
	public double speed; //creates a variable for the driving speed
	public double startTime; //creates a variable for the start time
	public double passedTime; //creates a variable for the time passed
	
	public DriveForward (double driveTime, double speed) {
		this.driveTime = driveTime; //sets this class' driveTime variable equal to the value entered
		this.speed = speed; //same with speed
		requires((Subsystem)driveTrain); //requests exclusive use of the driveTrain subsystem
		startTime = Utility.getFPGATime(); //sets a variable for the start time
		passedTime = 0; //sets a variable for the time passed
	}

	@Override
	protected void initialize() {
		driveTrain.tankDrive(0,0);
	}

	@Override
	public String getConsoleIdentity() {
		return null;
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(0, 0);
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime; //sets a variable for the time passed
		driveTrain.tankDrive(-speed, -speed); //makes the robot move forward at a speed equal to the value entered
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		if((passedTime / 1000000) > driveTime) {
			System.out.println("DriveForward is done");
			return true;
		} else
			return false;
	}
	
	public static double rotation() {
		return -(driveTrain.getAngle()/45);
	}

	public static double deadzone (double d) {
		if (Math.abs(d) < .1) {
            return 0;
        }
		if (d == 0) {
            return 0;
        }
		return (d / Math.abs(d)) * ((Math.abs(d) - .1) / (1 - .1));
	}

}