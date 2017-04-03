package org.usfirst.frc.team1014.robot;

import org.usfirst.frc.team1014.robot.utils.RobotProfile;

public class PracticeRobotProfile extends RobotProfile {

	@Override
	public String getName() {
		return "Practice Robot";
	}

	@Override
	public double getWidth() {
		return .48;
	}

	@Override
	public double getLength() {
		return .69;
	}

	@Override
	public boolean hasDriveTrain() {
		return true;
	}

	@Override
	public boolean hasShooter() {
		return false;
	}

	@Override
	public boolean hasClimber() {
		return false;
	}

	@Override
	public int getCANID_DRIVE_A() {
		return 14;
	}

	@Override
	public int getCANID_DRIVE_B() {
		return 15;
	}

	@Override
	public int getCANID_DRIVE_C() {
		return 17;
	}

	@Override
	public int getCANID_DRIVE_D() {
		return 11;
	}

	@Override
	public int getCANID_PIVOT_A() {
		return 13;
	}

	@Override
	public int getCANID_PIVOT_B() {
		return 16;
	}

	@Override
	public int getCANID_PIVOT_C() {
		return 18;
	}

	@Override
	public int getCANID_PIVOT_D() {
		return 12;
	}

	@Override
	public int getCANID_SHOOTER() {
		return -1;
	}

	@Override
	public int getCANID_AGITATOR() {
		return -1;
	}

	@Override
	public int getCANID_CLIMBER() {
		return -1;
	}

}
