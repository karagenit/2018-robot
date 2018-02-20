/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.techhounds;

import com.techhounds.commands.Dashboard;
import com.techhounds.commands.auton.AutonLauncher;
import com.techhounds.subsystems.ArmActuator;
import com.techhounds.subsystems.ArmIntake;
import com.techhounds.subsystems.ArmTilt;
import com.techhounds.subsystems.Drivetrain;
import com.techhounds.subsystems.Gyroscope;
import com.techhounds.subsystems.PowerPack;
import com.techhounds.subsystems.PullVision;
import com.techhounds.subsystems.Transmission;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	// Robot Subsystems
	public static final ArmActuator arm = new ArmActuator();
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Gyroscope gyro = new Gyroscope();
	public static final ArmIntake intake = new ArmIntake();
	public static final PowerPack powerPack = new PowerPack();
	public static final Transmission transmission = new Transmission();
	public static final ArmTilt tilt = new ArmTilt();
	public static final PullVision vision = new PullVision();
			
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		AutonLauncher.addAutonChoices();
		Dashboard.initDashboard();
		OI.setupDriver();
		OI.setupOperator();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		AutonLauncher.runAuton();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		Dashboard.updateDashboard();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
		// Don't need to start teleop commands, as they're all set as defaults
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Dashboard.updateDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {}
}