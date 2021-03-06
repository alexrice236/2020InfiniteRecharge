/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.IntakeCells;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  
  public WPI_TalonSRX intakeMotor = new WPI_TalonSRX(RobotMap.intakeMotor);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new IntakeCells());
  }


  public void intakeMethod(){
  if(Robot.oi.getCopilotController().getRawButton(RobotMap.joystickRightBumper)){
    Robot.intake.intakeMotor.set(0.5);
  }else if(Robot.oi.getCopilotController().getRawButton(RobotMap.joystickLeftBumper)){
    Robot.intake.intakeMotor.set(-0.5);
  }else{
  Robot.intake.intakeMotor.set(0);
  }
}


  public void stopIntake(){
    intakeMotor.set(0);
  }

}