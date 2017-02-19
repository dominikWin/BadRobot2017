package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleDrive extends Command {
	
	XboxController controller;
	
	public TeleDrive(XboxController controller) {
		super();
		this.controller = controller;
	}

	protected void initialize() {
		// requires(DriveTrain.getInstance());
	}

	public void execute() {

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
