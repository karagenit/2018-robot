package com.techhounds.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.techhounds.Constants;
import com.techhounds.Dashboard.DashboardUpdatable;
import com.techhounds.RobotMap;
import com.techhounds.RobotUtilities;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem implements DashboardUpdatable {
	
	private WPI_TalonSRX intakeLeft;
	private WPI_TalonSRX intakeRight;
	
	public Intake() {
		intakeLeft = RobotUtilities.getTalonSRX(RobotMap.INTAKE_LEFT);
		intakeRight = RobotUtilities.getTalonSRX(RobotMap.INTAKE_RIGHT);
		configure(intakeLeft);
		configure(intakeRight);
	}
	
	private void configure(WPI_TalonSRX talon) {
		talon.configOpenloopRamp(0.1, RobotUtilities.CONFIG_TIMEOUT);
	}
	
	public void setPower(double power){
		intakeLeft.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
		intakeRight.set(ControlMode.PercentOutput, RobotUtilities.constrain(power));
	}
	
	public double getLeftPower() {
		return intakeLeft.get();
	}
	
	public double getRightPower() {
		return intakeRight.get();
	}
	
	/**
	 * NOTE: the reading tends to peak around the center of the blue collector
	 * wheels, then drops off to ~400 when you get up to the metal bar.
	 * @return value approx 180 to 750
	 */
	public double getIRSensor() {
		return intakeLeft.getSensorCollection().getAnalogIn();
	}
	
	public boolean isCubeDetected() {
		return getIRSensor() > Constants.IR_THRESHOLD;
	}

    public void initDefaultCommand() {
    	// GamepadIntakeControl set in OI
    }

	@Override
	public void initSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSD() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initDebugSD() {
		SmartDashboard.putData(this);
		SmartDashboard.putData("Intake In", new SetIntakePower(1));
		SmartDashboard.putData("Intake Out", new SetIntakePower(-1));
	}

	@Override
	public void updateDebugSD() {
		SmartDashboard.putNumber("Intake Power", intakeLeft.getMotorOutputPercent());
		SmartDashboard.putNumber("Intake Current", intakeLeft.getOutputCurrent());
		SmartDashboard.putNumber("IR Sensor", intakeLeft.getSensorCollection().getAnalogIn());
	}
}