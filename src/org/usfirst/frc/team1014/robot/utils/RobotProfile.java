package org.usfirst.frc.team1014.robot.utils;

public abstract class RobotProfile {
	/**
	 * @return a {@link String} representing the name of the robot profile
	 */
	public abstract String getName();

	/**
	 * @return the width of the robot in meters
	 */
	public abstract double getWidth();

	/**
	 * @return the length of the robot in meters
	 */
	public abstract double getLength();

	/**
	 * @return true if the robot has a drive train, false if it does not
	 */
	public abstract boolean hasDriveTrain();

	/**
	 * @return true if the robot has a shooter, false if it does not
	 */
	public abstract boolean hasShooter();

	/**
	 * @return true if the robot has a climber, false if it does not
	 */
	public abstract boolean hasClimber();

	/**
	 * @return the CAN ID of drive motor A, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_DRIVE_A();

	/**
	 * @return the CAN ID of drive motor B, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_DRIVE_B();

	/**
	 * @return the CAN ID of drive motor C, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_DRIVE_C();

	/**
	 * @return the CAN ID of drive motor D, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_DRIVE_D();

	/**
	 * @return the CAN ID of pivot motor A, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_PIVOT_A();

	/**
	 * @return the CAN ID of pivot motor B, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_PIVOT_B();

	/**
	 * @return the CAN ID of pivot motor C, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_PIVOT_C();

	/**
	 * @return the CAN ID of pivot motor D, or -1 if this system does not exist
	 *         on this robot
	 */
	public abstract int getCANID_PIVOT_D();

	/**
	 * @return the CAN ID of the shooter motor, or -1 if this system does not
	 *         exist on this robot
	 */
	public abstract int getCANID_SHOOTER();

	/**
	 * @return the CAN ID of the agitator motor, or -1 if this system does not
	 *         exist on this robot
	 */
	public abstract int getCANID_AGITATOR();

	/**
	 * @return the CAN ID of the climber motor, or -1 if this system does not
	 *         exist on this robot
	 */
	public abstract int getCANID_CLIMBER();
}
