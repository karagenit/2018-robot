package com.techhounds.powerpack;

import com.techhounds.Robot;
import com.techhounds.powerpack.PowerPack.PowerPackState;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadClimberControl extends Command {

	private final XboxController controller;
	private final int axis;

    public GamepadClimberControl(XboxController controller, int axis) {
    	requires(Robot.powerPack);
    	this.controller = controller;
    	this.axis = axis;
    }

    protected void initialize() {
    	Robot.powerPack.setState(PowerPackState.CLIMBER);
    }

    protected void execute() {
    	double forward = -controller.getRawAxis(axis);
    	
    	Robot.intake.setPower(forward);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}