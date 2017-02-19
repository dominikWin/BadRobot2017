package org.usfirst.frc.team1014.robot.util;

import com.ctre.CANTalon;

public class SwerveWheel {
	Vector2d position;
	CANTalon drive, pivot;
	String name;
	int min, max, range;

	int encoderOffset;

	public SwerveWheel(String name, Vector2d position, CANTalon drive, CANTalon pivot, int min, int max, int offset) {
		this.position = position;
		this.name = name;
		this.drive = drive;
		this.pivot = pivot;
		this.min = min;
		this.max = max;
		this.encoderOffset = offset;
		range = max - min;
	}
	
	public void drive(Vector2d translation, double rotation) {
		drive(translation.add(position.perpendicularCCW().scale(rotation)));
	}

	public void drive(Vector2d translation) {
		int trans_encode = encodeVector(translation);
		System.out.println("TRANS_ANG " + trans_encode);
		double angleDeviation = ((double) posMod(getAngle() - trans_encode, 1024)) / (double) 1024d;
		double pivot_speed = -rotateFunc(angleDeviation);
		double drive_speed = translation.magnitude();
		if(angleDeviation > .25 && angleDeviation < .75)
			drive_speed *= -1;
		pivot.set(pivot_speed);
		drive.set(drive_speed);
	}

	private int encodeVector(Vector2d translation) {
		return posMod((int) (-Math.atan2(translation.getX(), translation.getY()) / (2d * Math.PI) * 1024d),
				1024);
	}

	public double rotateFunc(double angle) {
		if (angle < .25)
			return angle * 4;
		if (angle > .75)
			return (angle - 1) * 4;
		return (angle - .5) * 4;
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
