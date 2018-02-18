package com.techhounds.commands;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class SetTilt extends Command {
	
	private double tiltNumber = 0;

    public SetTilt(double tiltNumber) {
        requires(Robot.tilt);
    	this.tiltNumber = tiltNumber;
    }
    
    public enum TiltPosition{
    	up, down
    }
    
    public SetTilt(TiltPosition tilt) {
    	requires(Robot.tilt);
    	switch(tilt) {
    	case up:
    		tiltNumber = 100;
    		break;
    	case down:
    		tiltNumber = 0;
    		break;
    	default:
    		tiltNumber = 0;
    		break;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.tilt.setPosition(tiltNumber);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
