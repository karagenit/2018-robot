package com.techhounds.drivetrain;

import com.techhounds.OI;
import com.techhounds.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class ArcadeDrive extends Command {
	
	private final XboxController controller;
	private final int forwardAxis;
	private final int turnAxis;
	
    public ArcadeDrive(XboxController controller, int forward, int turn) {
        requires(Robot.drivetrain);
        this.controller = controller;
        this.forwardAxis = forward;
        this.turnAxis = turn;
    }

    protected void initialize() {}

    /**
     *
     */
    protected void execute() {
    	double forward = controller.getRawAxis(forwardAxis);
    	
    	if (OI.driveDirection) {
    		forward *= -1;
    	}
    	
    	double turn = -controller.getRawAxis(turnAxis); //negative is intentional
    	
    	if (Math.abs(forward) < OI.CONTROLLER_DEADBAND) {
    		forward = 0;
    	}
    	
    	if (Math.abs(turn) < 0.08) {
    		turn = 0;
    	}
    	
    	turn *= 0.5;
    	
    	// limit drive speed if elevator up
//    	if (Robot.powerPack.getWinchPosition() > 500000) {
//    		forward *= 0.5;
//    	}
    	
    	Robot.drivetrain.setPower(forward+turn, forward-turn);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drivetrain.setPower(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
