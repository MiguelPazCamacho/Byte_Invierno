/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //Chasis puertos
  
    public static int id_m_chasis_fl =9;
    public static int id_m_chasis_fr =3;
    public static int id_m_chasis_bl =1;
    public static int id_m_chasis_br =14;


    //Chasis Sims

  
    public static TalonSRX M_Chasis_fr = new TalonSRX(id_m_chasis_fr);
    public static TalonSRX M_Chasis_fl = new TalonSRX(id_m_chasis_fl);
    public static TalonSRX M_Chasis_br = new TalonSRX(id_m_chasis_br);
    public static TalonSRX M_Chasis_bl = new TalonSRX(id_m_chasis_bl);

    public static void init(){

      //Chasis sims
    M_Chasis_fl.set(ControlMode.PercentOutput, 0);
    M_Chasis_fl.setNeutralMode(NeutralMode.Brake);
    M_Chasis_fl.setInverted(false);

    M_Chasis_bl.set(ControlMode.PercentOutput, 0);
    M_Chasis_bl.setNeutralMode(NeutralMode.Brake);
    M_Chasis_bl.follow(M_Chasis_fl);
    M_Chasis_bl.setInverted(false);

    M_Chasis_fr.set(ControlMode.PercentOutput, 0);
    M_Chasis_fr.setNeutralMode(NeutralMode.Brake);
    M_Chasis_fr.setInverted(false);

    M_Chasis_br.set(ControlMode.PercentOutput, 0);
    M_Chasis_br.setNeutralMode(NeutralMode.Brake);
    M_Chasis_br.follow(M_Chasis_fr);
    M_Chasis_br.setInverted(false);




    }


}
