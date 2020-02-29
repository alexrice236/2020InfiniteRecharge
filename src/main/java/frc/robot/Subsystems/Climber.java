
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.Climb;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX leftClimb = new WPI_TalonSRX(RobotMap.leftClimb);
  public WPI_TalonSRX rightClimb = new WPI_TalonSRX(RobotMap.rightClimb);

  public VictorSPX extendClimb = new VictorSPX(RobotMap.extendClimb);

  private SpeedControllerGroup climber = new SpeedControllerGroup(leftClimb, rightClimb);


  @Override
  public void initDefaultCommand() {
   setDefaultCommand(new Climb());
  }

  public void ratchetingClimb(){
    climber.set(Robot.oi.getCopilotController().getRawAxis(RobotMap.leftTriggerAxis) * 0.6 + -Robot.oi.getCopilotController().getRawAxis(RobotMap.rightTriggerAxis) * 0.6);
    
  }

  public void extendingHook(){
    if(Robot.oi.getCopilotController().getRawButton(RobotMap.joystickButtonY)){
      extendClimb.set(ControlMode.PercentOutput, 0.4);
    }else if(Robot.oi.getCopilotController().getRawButton(RobotMap.joystickButtonA)){
      extendClimb.set(ControlMode.PercentOutput, -0.4);
    }else{
      extendClimb.set(ControlMode.PercentOutput, 0);
    }
  }

}
