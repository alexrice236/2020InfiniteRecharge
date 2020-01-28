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
       // pilotButtonY.whenReleased(new PositionArm(1));

       if(pilotButtonY.get()){new PositionArm(1);}

    /*Button pilotButtonA = new JoystickButton(pilotController, RobotMap.joystickButtonA);
        pilotButtonA.whenPressed(new PositionArm(2));

    Button pilotButtonX = new JoystickButton(pilotController, RobotMap.joystickButtonX);
        pilotButtonX.whenPressed(new PositionArm(3));*/


}

public Joystick getPilotController() {
    return pilotController;
}


}
