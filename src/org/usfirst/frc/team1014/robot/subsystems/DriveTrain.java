package org.usfirst.frc.team1014.robot.subsystems;

import java.util.*;

import org.usfirst.frc.team1014.robot.RobotMap;
import org.usfirst.frc.team1014.robot.util.SwerveWheel;
import org.usfirst.frc.team1014.robot.util.Vector2d;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	List<SwerveWheel> swerveWheels;

	public DriveTrain() {
		swerveWheels = new ArrayList<SwerveWheel>() {
			{
				add(new SwerveWheel("A", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_A),
						new CANTalon(RobotMap.SWERVE_PIVOT_A), 10, 863, 851));
				add(new SwerveWheel("B", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_B),
						new CANTalon(RobotMap.SWERVE_PIVOT_B), 10, 863, 370));
				add(new SwerveWheel("C", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_C),
						new CANTalon(RobotMap.SWERVE_PIVOT_C), 10, 863, 836));
				add(new SwerveWheel("D", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_D),
						new CANTalon(RobotMap.SWERVE_PIVOT_D), 10, 863, 578));
			}
		};
	}

	public void drive(final Vector2d translation) {
		swerveWheels.forEach((w) -> w.drive(translation));
	}

	public void drive(final double rotation, final Vector2d translation) {
	}

	public void update() {
		swerveWheels.forEach(SwerveWheel::update);
	}

	public void rawDrive(double speed) {
		swerveWheels.forEach((w) -> w.rawDrive(speed));
	}

	public void rawPivot(double speed) {
		swerveWheels.forEach((w) -> w.rawPivot(speed));

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
