/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.MoveArm;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {

  public WPI_TalonFX armMotor = new WPI_TalonFX(RobotMap.armMotor);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new MoveArm());
  }

  public void moveArm(){
   if(Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) < 0 && Robot.upperLimit.get()){
      armMotor.set(-Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) * 0.2);
    }else if(Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) > 0 && Robot.lowerLimit.get()){
      armMotor.set(-Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) * 0.2);
    }else{
    armMotor.set(-Robot.oi.getCopilotController().getRawAxis(RobotMap.leftJoystickYAxis) * 0.35);
    }
  }
  
  public void stopArm(){
    armMotor.set(0);
}
}
