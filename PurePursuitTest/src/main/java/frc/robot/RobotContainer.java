// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.commands.*;
import frc.robot.commands.PurePursuit.*;
import frc.robot.commands.aim.*;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.OnBoardIO.ChannelMode;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Button;

public class RobotContainer
{
	private final OnBoardIO onboardIO = new OnBoardIO(ChannelMode.INPUT, ChannelMode.INPUT);
	private final Vision2 vision = new Vision2(
		new Transform2d(new Translation2d(70.78 / 1000, 10.0 / 1000), new Rotation2d()), 
		new Pose2d(0, 5.6982, new Rotation2d())
	);
	private final Drivetrain dt = new Drivetrain(vision, new Pose2d(Units.feetToMeters(10), 5.6892, Rotation2d.fromDegrees(180)));

	private final Joystick controller = new Joystick(0);

	SendableChooser<Command> chooser = new SendableChooser<>();

	/** The container for the robot. Contains subsystems, OI devices, and commands. */
	public RobotContainer()
	{
		dt.setDefaultCommand(new RunCommand(() -> 
			dt.setWheelSpeeds(
				(-controller.getRawAxis(1) * Constants.kMaxSpeedMetersPerSecond) + (controller.getRawAxis(0) * Constants.kMaxSpeedMetersPerSecond),
				(-controller.getRawAxis(1) * Constants.kMaxSpeedMetersPerSecond) - (controller.getRawAxis(0) * Constants.kMaxSpeedMetersPerSecond)), dt));
		
		// Example of how to use the onboard IO
		Button onboardButtonA = new Button(onboardIO::getButtonAPressed);
		onboardButtonA
				.whenActive(new PrintCommand("Button A Pressed"))
				.whenInactive(new PrintCommand("Button A Released"));

		chooser.setDefaultOption("Pure Pursuit", new FollowTrajCommand(dt));
		chooser.addOption("Set Wheel Speeds test", new RunCommand(() -> dt.setWheelSpeeds(0.3, 0.3), dt));
		chooser.addOption("Ramsete", new RamseteTrajCommand(dt));
		chooser.addOption("Sharp square", new Square(dt));
		chooser.addOption("Profiled Square", new ProfiledSquare(dt));
		chooser.addOption("Ultimate DTD", new UltimateDTD(0.3, dt));
		chooser.addOption("AimCommand", new AimCommand(dt, vision).perpetually());
		chooser.addOption("AimCommand2", new AimCommand2(dt, vision).perpetually());
		chooser.addOption("SimpleAimCommand", new SimpleAimCommand(dt, vision).perpetually());
		chooser.addOption("Profiled TTA", new TTAProfiled(90, dt));

		SmartDashboard.putData("Auto chooser", chooser);
	}

	public Command getAutonomousCommand() { return chooser.getSelected(); }

	public void disabledInit()
	{
		dt.resetPose();
		dt.resetSensors();
	}
}
