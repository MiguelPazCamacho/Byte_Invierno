/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Robot_Elevador extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double Move_up;
  public double Move_down;
  public double E_aceleracion;
  public CANPIDController m_pidController;
  public CANEncoder M_encoder;
  public CANDigitalInput LS_UP,LS_DN;
  public double kP,kI,kD,kIz,kFF,kMaxOutput,kMinOutput;
  public double Pri_Rocket =0; 
  public double Seg_Rocket = 20;
  double actual_position;
  private CANEncoder elevador_encoder;
  private double Posicion_cancha;
  double maxVel, minVel,maxAcc,allowedErr;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  //Esta parte es el PID aún falta obtner los valores de P I D
  public Robot_Elevador(){
    m_pidController = RobotMap.M_elevador.getPIDController();

    // Encoder object created to display position values
    M_encoder = RobotMap.M_elevador.getEncoder();
    LS_UP= RobotMap.M_elevador.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
    LS_DN =RobotMap.M_elevador.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
    LS_DN.enableLimitSwitch(true);
    LS_UP.enableLimitSwitch(false);
    // PID coefficients
    kP = .5; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    
   

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);
    
    //System.out.println("VALOR DEL ENCODER ELEVADOR: " + M_encoder.getPosition());
  }

  public void Stop_Elevador(){
    RobotMap.M_elevador.set(0);
  }

  public void Move_Elevador(){
    actual_position=M_encoder.getPosition();
    Move_up= Robot.m_oi.Stick_Sub.getRawAxis(3);
    Move_down= -Robot.m_oi.Stick_Sub.getRawAxis(2);
    
    if (Move_up > .15){
      E_aceleracion= Move_up;
      RobotMap.M_elevador.set(E_aceleracion);

    } else if (Move_down < -.15){
      E_aceleracion=Move_down;
      RobotMap.M_elevador.set(E_aceleracion);
    }else{
      if (Robot.m_oi.Stick_Sub.getRawButton(2)){
        m_pidController.setReference(Pri_Rocket, ControlType.kPosition);
      }else if(Robot.m_oi.Stick_Sub.getRawButton(4)){
        m_pidController.setReference(Seg_Rocket, ControlType.kPosition);
      }else{
        m_pidController.setReference(actual_position, ControlType.kPosition);
      }

        //System.out.println("VALOR DEL ENCODER ELEVADOR: " + M_encoder.getPosition());

    }

    
  }

}
