package org.usfirst.frc.team1014.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleopGroup extends CommandGroup {
	
	public TeleopGroup(XboxController controller) {
		this.addParallel(new TeleDrive(controller));
	}

}
