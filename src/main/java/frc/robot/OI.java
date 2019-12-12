/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Robot_Autonomo_Prueba;
import frc.robot.commands.Robot_Chasis_Mover_Linea_Recta;
import frc.robot.commands.Robot_Climb_Contraer;
import frc.robot.commands.Robot_Climb_Expander;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick Stick_C;
  public Joystick Stick_Sub;
  public Button ButtonA1;
  public Button ButtonB1;
  public Button ButtonX1;

  public Button ButtonA2;
  public Button ButtonB2;
  public Button ButtonX2;
  public Button ButtonY2;

    
  public OI(){
    Stick_C = new Joystick(0);
    Stick_Sub = new Joystick(1);

    ButtonA1 = new JoystickButton(Stick_C, 1);
    ButtonX1 = new JoystickButton(Stick_C, 3);


    ButtonA1.whenPressed(new Robot_Autonomo_Prueba());
    //ButtonA1.whenPressed(new Robot_Climb_Expander());
    //ButtonX1.whenPressed(new Robot_Climb_Contraer());
    ButtonX1.whenPressed(new Robot_Chasis_Mover_Linea_Recta());

  }
  

  

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
