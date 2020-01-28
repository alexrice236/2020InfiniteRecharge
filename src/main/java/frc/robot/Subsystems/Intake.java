/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Commands.IntakeCells;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  
  public CANSparkMax armMotor = new CANSparkMax(RobotMap.armMotor, MotorType.kBrushless);
  public WPI_VictorSPX intakeMotor = new WPI_VictorSPX(RobotMap.intakeMotor);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new IntakeCells());
  }

  public double getArmPosition(){
    return armMotor.getEncoder().getPosition();
  }

  /*public void moveCells(){
    if(Robot.oi.getPilotController().getRawButton(RobotMap.joystickRightBumper)){
        intakeMotor.set(0.5);
    }else if(Robot.oi.getPilotController().getRawButton(RobotMap.joystickLeftBumper)){
        intakeMotor.set(-0.5);
    }else{
      intakeMotor.set(0);
    }}*/

  public void stopIntake(){
    intakeMotor.set(0);
  }

  public void stopArm(){
    armMotor.set(0);
}

}
