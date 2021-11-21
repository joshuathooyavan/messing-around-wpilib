// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
	public static final double kMaxSpeedMetersPerSecond = 0.8 / 2;
	public static final double kMaxAccelerationMetersPerSecondSquared = kMaxSpeedMetersPerSecond / 2;
	public static final double kMaxTurnSpeedDegreesPerSecond = 360 / 1;
	public static final double kMaxTurnAccelerationDegreesPerSecondSquared = kMaxTurnSpeedDegreesPerSecond / 1;
	
	public static final double kLookAheadDistance = 0.15;


	public static final double kPSetWheelSpeeds = 0.15;

	public static final double kPDTD = 6;
	public static final double kToleranceMeters = 0.05;
	public static final double kPTTA = 0.014;
	public static final double kToleranceDegrees = 6;


	// perfect feedforwards! woohoo! hand tuned babie!
	public static final double lksVolts = 0.9;
	public static final double lkvVoltSecondsPerMeter = 9.5;
	
	public static final double rksVolts = 0.7;
	public static final double rkvVoltSecondsPerMeter = 8.3;
}