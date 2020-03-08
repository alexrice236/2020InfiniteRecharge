/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoDriveForward extends Command {

  int driveDistance;

  public AutoDriveForward(int setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    driveDistance = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.useDrive = true;
    Robot.drivetrain.brakeMode();
    Robot.drivetrain.resetEncoders();
    Robot.drivetrain.setSetpoint(driveDistance);
    Robot.drivetrain.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.pidInput = Robot.drivetrain.getAbsoluteDistance();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drivetrain.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stopDrive();
    Robot.drivetrain.useDrive = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
