/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Robot_Intake_Discos extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double State;
  public double M_intake;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    boolean ls_Run; 

  }

  public void Stop_Intake_Discos(){
    
    RobotMap.M_intake_R.set(ControlMode.PercentOutput, 0);
    

  }

  public void Act_Intake_Discos(){

    State= Robot.m_oi.Stick_Sub.getRawAxis(5);

    if (State > 0.2){
      M_intake= 1;

    }else if (State<-0.2){

      M_intake=-1;

    }else {

      M_intake=0;

    }

    
    RobotMap.M_intake_R.set(ControlMode.PercentOutput, M_intake);
    
  }

    /*
  public void Run_Intake_Discos(){

    RobotMap.M_intake_R.set(ControlMode.PercentOutput, 1);

  }

  public void Drop_Intake_Discos(){

    RobotMap.M_intake_R.set(ControlMode.PercentOutput, -1);

  }
  */

}
