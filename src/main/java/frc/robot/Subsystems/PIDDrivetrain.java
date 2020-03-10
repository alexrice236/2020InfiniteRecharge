/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveWithJoysticks;

/**
 * Add your docs here.
 */
public class PIDDrivetrain extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  private WPI_TalonFX leftBackMotor = new WPI_TalonFX(RobotMap.leftBackMotor);
  private WPI_TalonFX leftFrontMotor = new WPI_TalonFX(RobotMap.leftFrontMotor);
  private WPI_TalonFX rightBackMotor = new WPI_TalonFX(RobotMap.rightBackMotor);
  private WPI_TalonFX rightFrontMotor = new WPI_TalonFX(RobotMap.rightFrontMotor);


  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftBackMotor, leftFrontMotor);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightBackMotor, rightFrontMotor);
  
  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  public boolean useReverseDrive = false;

  public boolean useTurn = false;
  public boolean useDrive = false;

  public double pidInput;

  
  public PIDDrivetrain() {
    // Intert a subsystem name and PID values here
    super("PIDDrivetrain", 0, 0, 0);
    setAbsoluteTolerance(40);

    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
       setDefaultCommand(new DriveWithJoysticks());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return getLeftEncoderPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
      tankDrive(output, output);

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

  public int getLeftEncoderPosition(){
    return leftBackMotor.getSelectedSensorPosition();
  }

  public int getRightEncoderPosition(){
    return rightBackMotor.getSelectedSensorPosition();

  }

  public double getAbsoluteDistance(){
    double leftRotations = getLeftEncoderPosition() / 4096;
    double rightRotations = getRightEncoderPosition() / -4096;

    return (leftRotations + rightRotations) * 3;
  }

  public void brakeMode(){
    leftBackMotor.setNeutralMode(NeutralMode.Brake);
    leftFrontMotor.setNeutralMode(NeutralMode.Brake);
    rightBackMotor.setNeutralMode(NeutralMode.Brake);
    rightFrontMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void resetEncoders(){
    leftBackMotor.setSelectedSensorPosition(0);
    rightBackMotor.setSelectedSensorPosition(0);
  }

}
