package org.usfirst.frc.team1014.robot.util;

import com.ctre.CANTalon;

public class SwerveWheel {
	CANTalon drive, pivot;
	String name;
	int min, max, range;

	int encoderOffset;

	public SwerveWheel(String name, Vector2d position, CANTalon drive, CANTalon pivot, int min, int max) {
		this.name = name;
		this.drive = drive;
		this.pivot = pivot;
		this.min = min;
		this.max = max;
		range = max - min;
	}

	public void update() {
		System.out.println(name + ": " + getAngleTrue());
	}

	public void zeroEncoder() {
		encoderOffset = pivot.getAnalogInRaw();
	}

	private int getAngleTrue() {
		return (int) ((((pivot.getAnalogInPosition()) % 1024) - min) * (1024d / (double) range));
	}

	public void rawDrive(double speed) {
		drive.set(speed);
	}

	public void rawPivot(double speed) {
		pivot.set(speed);
	}
}
