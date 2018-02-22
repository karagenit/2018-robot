package com.techhounds.drivetrain;

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
     * TODO: do we want to square/cube input?
     */
    protected void execute() {
    	double forward = -controller.getRawAxis(forwardAxis); //negatives are intentional
    	double turn = -controller.getRawAxis(turnAxis);
    	
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