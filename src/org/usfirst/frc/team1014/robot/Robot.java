package org.usfirst.frc.team1014.robot;

import org.usfirst.frc.team1014.robot.commands.AutoGroup;
import org.usfirst.frc.team1014.robot.commands.CalibGroup;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1014.robot.util.Vector2d;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	CommandGroup teleopGroup;
	CommandGroup autoGroup;
	CommandGroup testGroup;

	DriveTrain driveTrain;

	public OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		driveTrain = new DriveTrain();
		teleopGroup = new CalibGroup(driveTrain);
		autoGroup = new AutoGroup();
	}

	/*
	 * An Init function is called whenever the robot changes state.
	 */

	private void stateChangeInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopInit() {
		stateChangeInit();
		Scheduler.getInstance().add(teleopGroup);
	}

	@Override
	public void autonomousInit() {
		stateChangeInit();
		Scheduler.getInstance().add(autoGroup);
	}

	@Override
	public void testInit() {
		stateChangeInit();
		Scheduler.getInstance().add(testGroup);
	}

	@Override
	public void disabledInit() {
		stateChangeInit();
	}

	/*
	 * Periodic commands are called every 20m by the system. If it does not
	 * return within 20ms it will wait until the last one returned.
	 */

	private void periodic() {
		// Scheduler.getInstance().run();
		driveTrain.update();
		driveTrain.drive(new Vector2d(oi.xboxController0.getX(Hand.kLeft), -oi.xboxController0.getY(Hand.kLeft)));
	}

	@Override
	public void teleopPeriodic() {
		periodic();
	}

	@Override
	public void autonomousPeriodic() {
		periodic();
	}

	@Override
	public void testPeriodic() {
		periodic();
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void robotPeriodic() {
	}
}
