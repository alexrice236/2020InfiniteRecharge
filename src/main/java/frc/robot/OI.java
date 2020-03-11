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
import frc.robot.Commands.*;



/**
 * Add your docs here.
 */
public class OI {

    public Joystick pilotController;
    public Joystick copilotController;

public OI(){

    pilotController = new Joystick(0);
    copilotController = new Joystick(1);

    Button pilotB = new JoystickButton(pilotController, RobotMap.joystickButtonB);
    pilotB.toggleWhenPressed(new LedOnAndOff());

    Button pilotY = new JoystickButton(pilotController, RobotMap.joystickButtonY);
    pilotY.whileHeld(new AutoMoveRobotToTarget());


   

}

public Joystick getPilotController() {
    return pilotController;
}

public Joystick getCopilotController(){
    return copilotController;
}



}
