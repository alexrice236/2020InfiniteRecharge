/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.MoveArm;

/**
 * Add your docs here.
 */
public class Arm extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  public WPI_TalonSRX armMotor = new WPI_TalonSRX(RobotMap.armMotor);

  public Arm() {
    // Intert a subsystem name and PID values here
    super("Arm", 10, 0, -3);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    setAbsoluteTolerance(10);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
       setDefaultCommand(new MoveArm());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return getArmPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    armMotor.set(output);
  }

  public double getArmPosition(){
    return armMotor.getSelectedSensorPosition();
  }

  public void moveArm(){
    if(Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) < 0 && Robot.upperLimit.get()){
      armMotor.set(-0.05);
    }else if(Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) > 0 && Robot.lowerLimit.get()){
      armMotor.set(0);
    }else{
    armMotor.set(-Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) * 0.7);
    }
  }
  
  public void stopArm(){
    armMotor.set(0);
}

}
