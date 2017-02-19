package org.usfirst.frc.team1014.robot.commands.calib;

import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class PivotTestCommand extends Command {

	long endTime, runTime;
	DriveTrain driveTrain;
	double speed;

	public PivotTestCommand(double seconds, DriveTrain driveTrain, double speed) {
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
		driveTrain.rawPivot(speed);
	}

	@Override
	protected void end() {
		driveTrain.rawPivot(0);
	}

	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() > endTime;
	}

}
