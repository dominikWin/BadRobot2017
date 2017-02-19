package org.usfirst.frc.team1014.robot.commands.calib;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTestCommand extends Command {

	long endTime, runTime;
	DriveTrain driveTrain;
	double speed;

	public DriveTestCommand(double seconds, DriveTrain driveTrain, double speed) {
		super();
		requires(driveTrain);
		this.driveTrain = driveTrain;
		this.speed = speed;
		runTime = (long) (seconds * 1000d);
	}

	@Override
	protected void initialize() {
		endTime = System.currentTimeMillis() + runTime;
	}

	@Override
	protected void execute() {
		super.execute();
		driveTrain.rawDrive(speed);
	}

	@Override
	protected void end() {
		driveTrain.rawDrive(0);
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() > endTime;
	}

}
