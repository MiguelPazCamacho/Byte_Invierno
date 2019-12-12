/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class Robot_Climb extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void Climb_contraer(){

    RobotMap.ds_climb_piston.set(DoubleSolenoid.Value.kReverse);

  }
  public void Climb_expander(){

    RobotMap.ds_climb_piston.set(DoubleSolenoid.Value.kForward);
  }

  public void Climb_stop(){

    RobotMap.ds_climb_piston.set(DoubleSolenoid.Value.kOff);
  }





}
