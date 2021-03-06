package com.techhounds.auton.paths;

import com.techhounds.arm.GrabCube;
import com.techhounds.auton.util.CollectCube;
import com.techhounds.auton.util.DelayedCommand;
import com.techhounds.auton.util.DriveAngle;
import com.techhounds.auton.util.DriveStraight;
import com.techhounds.auton.util.DriveStraightUntilDetected;
import com.techhounds.auton.util.TurnToAngleGyro;
import com.techhounds.intake.IntakeUntilDetected;
import com.techhounds.intake.SetIntakePower;
import com.techhounds.powerpack.SetElevatorPosition;
import com.techhounds.powerpack.SetElevatorPosition.ElevatorPosition;
import com.techhounds.tilt.SetTiltPosition;
import com.techhounds.tilt.Tilt;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterRightSwitch extends CommandGroup {

    public CenterRightSwitch() {
    	// set to switch
    	addParallel(new DelayedCommand(new SetTiltPosition(Tilt.POS_DOWN), 1));
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 1.5));
    	
    	// drive up to switch
    	addSequential(new DriveAngle(20, 0, 0.4), 1);
    	addSequential(new DriveStraight(75, 0.6, 26), 3);
    	addSequential(new DriveStraight(25, 0.4, 26), 1);
    	addSequential(new TurnToAngleGyro(11.5), 0.5);
    	
    	// eject cube
    	addSequential(new SetIntakePower(-0.5), 0.5);
//    	
//    	// LAST CHECK
//    	
    	// back up to starting position
//    	addSequential(new DriveStraight(-10, -0.4), 1);
//    	addSequential(new TurnToAngleGyro(-30), 1);
    	addSequential(new DriveAngle(37, -0.4, 0), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-55, -0.6, 37), 3);
    	addSequential(new DriveStraight(-30, -0.4, 37), 2);
//    	
////    	// grab another one
    	addSequential(new TurnToAngleGyro(0), 1.5);
    	addParallel(new GrabCube(), 3);
    	addParallel(new IntakeUntilDetected(), 3);
    	addSequential(new DriveStraightUntilDetected(35, 0.5), 1);
    	addSequential(new DriveStraightUntilDetected(15, 0.2), 0.5);
//    	
//    	// line up to switch again
//    	addSequential(new WaitCommand(0.5));
    	addSequential(new TurnToAngleGyro(70), 1);
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.SWITCH), 0.5));
    	addSequential(new DriveStraight(40, 0.5), 2);
    	addSequential(new TurnToAngleGyro(10), 1);
    	addSequential(new DriveStraight(20, 0.4), 1);
    	
    	addSequential(new SetIntakePower(-0.5), 0.5);
    	
    	addParallel(new DelayedCommand(new SetElevatorPosition(ElevatorPosition.COLLECT), 0.5));
    	addSequential(new DriveStraight(-26, -0.4), 1);
    	addSequential(new TurnToAngleGyro(-55), 1);
    	addSequential(new CollectCube(35), 2);
    }
}
