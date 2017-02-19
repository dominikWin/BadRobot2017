package org.usfirst.frc.team1014.robot.util;

import com.ctre.CANTalon;

public class SwerveWheel {
	CANTalon drive, pivot;
	String name;
	int min, max, range;

	int encoderOffset;

	public SwerveWheel(String name, Vector2d position, CANTalon drive, CANTalon pivot, int min, int max, int offset) {
		this.name = name;
		this.drive = drive;
		this.pivot = pivot;
		this.min = min;
		this.max = max;
		this.encoderOffset = offset;
		range = max - min;
	}

	public void drive(Vector2d translation) {
		double pivot_speed = -rotateFunc((double) getAngle() / (double) 1024d);
		pivot.set(pivot_speed);
	}

	public double rotateFunc(double angle) {
		System.out.println(name + " ANGLE " + angle);
		if (angle < .25)
			return angle;
		if (angle > .75)
			return (angle - 1);
		return (angle - .5);
	}

	public void update() {
		System.out.println(name + ": " + getAngleTrue());
	}

	private int posMod(int a, int b) {
		int out = a % b;
		if (out < 0)
			out += b;
		return out;
	}

	private int getAngle() {
		int trueAngle = getAngleTrue();
		return posMod((trueAngle - encoderOffset), 1024);
	}

	private int getAngleTrue() {
		return (int) ((posMod((pivot.getAnalogInPosition()), 1024) - min) * (1024d / (double) range));
	}

	public void rawDrive(double speed) {
		drive.set(speed);
	}

	public void rawPivot(double speed) {
		pivot.set(speed);
	}
}
