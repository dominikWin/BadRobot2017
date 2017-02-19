package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.commands.calib.DriveTestCommand;
import org.usfirst.frc.team1014.robot.commands.calib.PivotTestCommand;
import org.usfirst.frc.team1014.robot.subsystems.DriveTrain;

public class CalibGroup extends TestGroup {
	public CalibGroup(DriveTrain driveTrain) {
		super();
		this.addSequential(new PivotTestCommand(3, driveTrain, 1));
		this.addSequential(new PivotTestCommand(3, driveTrain, -1));
		
		this.addSequential(new DriveTestCommand(3, driveTrain, .20));
		this.addSequential(new DriveTestCommand(3, driveTrain, -.20));
	}
}