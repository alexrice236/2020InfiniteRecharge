/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmToPosition extends Command {

  public int armPosition;
  
  public ArmToPosition(int setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
    setpoint = armPosition;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.arm.configureArmEncoder();
    Robot.arm.setSetpoint(armPosition);
    Robot.arm.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.arm.onTarget() || Robot.upperLimit.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.arm.stopArm();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
