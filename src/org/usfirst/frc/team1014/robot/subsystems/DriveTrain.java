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
						new CANTalon(RobotMap.SWERVE_PIVOT_A)));
				add(new SwerveWheel("B", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_B),
						new CANTalon(RobotMap.SWERVE_PIVOT_B)));
				add(new SwerveWheel("C", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_C),
						new CANTalon(RobotMap.SWERVE_PIVOT_C)));
				add(new SwerveWheel("D", new Vector2d(0, 0), new CANTalon(RobotMap.SWERVE_DRIVE_D),
						new CANTalon(RobotMap.SWERVE_PIVOT_D)));
			}
		};
	}

	public void drive(final double rotation, final Vector2d translation) {
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
