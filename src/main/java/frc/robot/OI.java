/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.PositionArm;

/**
 * Add your docs here.
 */
public class OI {

    public Joystick pilotController;

public OI(){

    pilotController = new Joystick(0);

    Button pilotButtonY = new JoystickButton(pilotController, RobotMap.joystickButtonY);
        pilotButtonY.whenPressed(new PositionArm());

    Button pilotButtonA = new JoystickButton(pilotController, RobotMap.joystickButtonA);
        pilotButtonA.whenPressed(new PositionArm());

    Button pilotButtonX = new JoystickButton(pilotController, RobotMap.joystickButtonX);
        pilotButtonX.whenPressed(new PositionArm());


}

public Joystick getPilotController() {
    return pilotController;
}


}
