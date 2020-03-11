/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoMoveRobotToTarget extends Command {

  private double rotations;
  private double minRotations = 0.5;
  private double kP = 0.1;

  private double leftSpeed;
  private double rightSpeed;

  private double x;

  public AutoMoveRobotToTarget() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");

    x = tx.getDouble(0.0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rotationsAdjust = -x;
    rotations = 0.0;
      if(x > 1.0){
        rotations = kP * rotationsAdjust - minRotations;
      }else if(x < 1.0){
        rotations = kP * rotationsAdjust + minRotations;
      }
    
      leftSpeed += rotations;
      rightSpeed -= rotations;

      Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivetrain.tankDrive(0, 0);
  }
}
