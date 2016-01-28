package org.usfirst.frc.team1014.robot.commands.autonomous;

import org.usfirst.frc.team1014.robot.commands.CommandBase;

public class AutoTurn extends CommandBase{
	
	public double degree, difference, proportion, sign;
	public static int rotationCoefficient = 60;
	
	public AutoTurn(double degree){
		this.degree = degree;
		driveTrain.resetMXPAngle();
		sign = Math.abs(degree) / degree;
	}

	@Override
	protected void initialize() {
		requires(driveTrain);
		driveTrain.tankDrive(0, 0);
		
	}

	@Override
	public String getConsoleIdentity() {
		return "AutoTurn";
	}

	@Override
	protected void end() {
		driveTrain.tankDrive(0, 0);
		
	}

	@Override
	protected void execute() {
		difference = driveTrain.getAngle() - degree;
		if(sign < 0){
			driveTrain.tankDrive(-(scale(rotation())), scale(rotation()));
		}
		if(sign > 0){
			driveTrain.tankDrive(scale(rotation()), -(scale(rotation())));
		}
	}

	@Override
	protected void interrupted() {
		System.out.println("AutoTurn was interrupted");
		
	}
	public double rotation()
	{
		return (difference/rotationCoefficient);
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(difference) < 5){
			return true;
		}else
		{
		return false;
		}
	}
    public static double scale(double d) {
    	
    	return(Math.abs(d) / 3);
    	
    	
    	
    	
    }
}
