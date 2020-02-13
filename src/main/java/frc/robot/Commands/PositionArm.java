/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.Subsystems.Intake;

public class PositionArm extends PIDCommand {
  /**
   * Creates a new PositionArm.
   */

  public PositionArm(double position) {
    super(
        // The controller that the command will use
        new PIDController(5, 0, -5),
        // This should return the measurement
        () -> Robot.intake.getArmPosition(),
        // This should return the setpoint (can also be a constant)
        () -> position,
        // This uses the output
        output -> {
          // Use the output here
          Robot.intake.armMotor.set(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(Robot.intake);
  }

  private void addRequirements(Intake intake) {
  }

  // R eturns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }

 
}
