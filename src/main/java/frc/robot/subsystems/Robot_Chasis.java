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
public class Robot_Chasis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  public double Rotacion;
  public double Adelante;
  public double Atras;
  public double Lado_R;
  public double Lado_L; 
  public double velocidad;
  public double Aceleracion;
  public double Giro;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


  public void Stop_Chasis(){
    
     //Chasis Sims
    RobotMap.M_Chasis_fl.set(ControlMode.PercentOutput, 0); 
    RobotMap.M_Chasis_fr.set(ControlMode.PercentOutput, 0);
    

  }

  public void Move_Chasis(){

    Rotacion= Robot.m_oi.Stick_C.getRawAxis(0);
    Adelante= Robot.m_oi.Stick_C.getRawAxis(3);
    Atras=  -Robot.m_oi.Stick_C.getRawAxis(2);

    
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

    RobotMap.M_Chasis_fl.set(ControlMode.PercentOutput, Lado_L);
    RobotMap.M_Chasis_fr.set(ControlMode.PercentOutput, Lado_R);


  }
}
