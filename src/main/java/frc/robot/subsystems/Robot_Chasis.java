/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Robot_Chasis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private double Rotacion;
  private double Adelante;
  private double Atras;
  private double Lado_R;
  private double Lado_L; 
  private double velocidad;
  private double Aceleracion;
  private double Giro;
  private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private CANPIDController m_pidController;
  private CANEncoder m_fr_encoder;
  private CANEncoder m_fl_encoder;
  private double Revoluciones, Llanta;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Robot_Chasis(){
    //Perimetro de la llanta en centimetros
    //Pi * Diametro
    Llanta= 3.14 *15.24;
    ///PID////////////////////////////////////////////////////////////////
    m_pidController = RobotMap.csm_m_chasis_fr.getPIDController();
    //m_pidController = RobotMap.csm_m_chasis_fl.getPIDController();
    // Encoder object created to display position values
    m_fr_encoder = RobotMap.csm_m_chasis_fr.getEncoder();
    //m_fl_encoder = RobotMap.csm_m_chasis_fl.getEncoder();
    // PID coefficients
    kP = 1; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    System.out.println( "El pid se esta utilizando");
  }


  public void Stop_Chasis(){
    
    //Chasis Neos
    RobotMap.csm_m_chasis_fl.set(0);
    RobotMap.csm_m_chasis_fr.set(0);
    /* //Chasis Sims
    RobotMap.M_Chasis_fl.set(ControlMode.PercentOutput, 0); 
    RobotMap.M_Chasis_fr.set(ControlMode.PercentOutput, 0);
    */
  }

  public void Chasis_Mover_Linea_recta(double Distancia){
    
    Revoluciones= 20;
    System.out.println("Revoluciones: " + Revoluciones);
    m_pidController.setReference( 20 , ControlType.kPosition);
    
    System.out.println("Se esta moviendo: " +m_fr_encoder.getPosition());
  }

  public void Move_Chasis(){
    Rotacion= Robot.m_oi.Stick_C.getRawAxis(0);
    Adelante= Robot.m_oi.Stick_C.getRawAxis(3);
    Atras=  -Robot.m_oi.Stick_C.getRawAxis(2);

    if (Robot.m_oi.Stick_C.getRawButton(2)){
      m_pidController.setReference( 20 , ControlType.kPosition);
    }

    /*
    if (Adelante>0.15||Atras<-0.15){
      velocidad = Adelante+Atras;
      Lado_R =velocidad;
      Lado_L = velocidad;
      if(Rotacion<-0.20 || Rotacion>0.20){
        Lado_R = -Rotacion*.5 + velocidad;
        Lado_L = Rotacion*.5 + velocidad;
      }
      }else if(Rotacion>0.20||Rotacion<-0.20){
        Lado_R = Rotacion;
        Lado_L = -Rotacion;
      }else{
        Lado_L=0;
        Lado_R=0;
      }

      if (Lado_R>1) {
        Lado_R= 1;
      }else if (Lado_R<-1){
        Lado_R=-1;
      }

      if (Lado_L>1){
        Lado_L=1;
      }else if(Lado_L<-1){
        Lado_L= -1;
      }
    */

      
    if (Adelante >= .15 && Adelante>Atras ) {
      Aceleracion= Math.abs(Adelante+Atras);
    } else if (Atras<=-.15 && Adelante == 0){
      Aceleracion= Atras;
    }else{
      Aceleracion=0;
    } 
        
    // En esta condicional se compara que nuestro poder en el stick sea mayor que
    // nuestra tolerancia
    //si no cumple con nuestra tolerancia el poder del stick será 0.
    if (Rotacion >= .20 || Rotacion<=-.20){
    Giro= Rotacion;
    //La variable Power_s pasa directamente porque cumple con nuestra tolerancia.
    }else{
    Giro=0;
    }
    
    //Aquí en estas dos variables se imprime la velocidad de cada lado
    //En este caso estamos sumando la aceleración del trigger más el valor de rotación del stick
    //La última parte donde estamos multiplicando ese valor de .3 es para reducir la fuerza del giro.
    
    Lado_L = (Aceleracion)+(Giro*.4);
    Lado_R = (Aceleracion)-(Giro*.4);
    
        
    
    //Al momento de sumar la aceleración y el poder de la rotación nos pueden dar valores 
    //mayores a 1 o -1, por eso estamos condicionando que nuestros dos valores del Chasis 
    //no pueden ser mayor que 1 o -1.
    if (Lado_L > 1){
    Lado_L=1;
    }else if (Lado_L <- 1){
    Lado_L=-1;
    }
    
    if (Lado_R > 1){
    Lado_R=1;
    }
    else if (Lado_R<-1){
    Lado_R=-1;
    }
    
    //Al final de todo nuestro proceso de condicionales y tolrerancias aplicamos 
    //nuestros valores correctos al motor.

    if (Robot.m_oi.Stick_C.getRawButton(2)){
    Lado_R  = Lado_R* 0.5;
    Lado_L  = Lado_L* 0.5;
    }

    //Chasis Neos
    RobotMap.csm_m_chasis_fr.set(Lado_R);
    RobotMap.csm_m_chasis_fl.set(Lado_L);

    /* //Chasis Sims
    RobotMap.M_Chasis_fl.set(ControlMode.PercentOutput, Lado_L);
    RobotMap.M_Chasis_fr.set(ControlMode.PercentOutput, Lado_R);
    */
  

    



    
  }
}
