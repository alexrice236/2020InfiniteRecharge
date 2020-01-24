/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveWithJoysticks;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonFX leftBackMotor = new WPI_TalonFX(RobotMap.leftBackMotor);
  private WPI_TalonFX leftFrontMotor = new WPI_TalonFX(RobotMap.leftFrontMotor);
  private WPI_TalonFX rightBackMotor = new WPI_TalonFX(RobotMap.rightBackMotor);
  private WPI_TalonFX rightFrontMotor = new WPI_TalonFX(RobotMap.rightFrontMotor);

  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftBackMotor, leftFrontMotor);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightBackMotor, rightFrontMotor);
  
  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  public boolean useReverseDrive = false;
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveWithJoysticks());
  }

  public void stopDrive(){
    drive.arcadeDrive(0, 0);
  }

  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }
    
  public void tankDrive(double leftSpeed, double rightSpeed){
      drive.tankDrive(leftSpeed, rightSpeed);
  }

  public boolean shouldUseReverseDrive(){
    return useReverseDrive;
  }

  public void setUseReverseDrive(boolean notCurrentDriveDirection){
    useReverseDrive = notCurrentDriveDirection;
  }


}
