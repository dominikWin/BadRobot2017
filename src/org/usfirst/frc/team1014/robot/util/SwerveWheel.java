package org.usfirst.frc.team1014.robot.util;

import com.ctre.CANTalon;

public class SwerveWheel {
	CANTalon drive, pivot;
	String name;
	
	public SwerveWheel(String name, Vector2d position, CANTalon drive, CANTalon pivot) {
		this.name = name;
		this.drive = drive;
		this.pivot = pivot;
	}
	
	public void rawDrive(double speed) {
		drive.set(speed);
	}
	
	public void rawPivot(double speed) {
		pivot.set(speed);
	}
 }
