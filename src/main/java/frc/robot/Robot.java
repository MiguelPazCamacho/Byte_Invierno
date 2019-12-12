/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.Robot_Chasis_Maindrive;
import frc.robot.commands.Robot_Elevador_Move;
import frc.robot.commands.Robot_Intake_Discos_Move;

import frc.robot.subsystems.Robot_Chasis;
import frc.robot.subsystems.Robot_Climb;
import frc.robot.subsystems.Robot_Elevador;
import frc.robot.subsystems.Robot_Intake_Discos;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  public static OI m_oi;

  public static Robot_Climb Robot_climb;
  public static Robot_Chasis Robot_chasis;
  public static Robot_Intake_Discos Robot_intake_discos;
  public static Robot_Elevador Robot_elevador;

  Command m_autonomousCommand;
  Command robot_autonomo_pruebaCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  Command robot_chasisCommand;
  Command robot_elevadorCommand;
  public static Command main_intake_discosCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    //Linea de codigo para que la camara aparezca en el smartdash
    //CameraServer.getInstance().startAutomaticCapture();

    RobotMap.init();
    
    Robot_chasis= new Robot_Chasis();
    Robot_intake_discos= new Robot_Intake_Discos();
    Robot_climb= new Robot_Climb();
    Robot_elevador= new Robot_Elevador();

    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser );

    m_oi= new OI();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }
  
  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    //PARA QUE EL AUTONOMO SEA SELECCIONADO DESDE EL M.CHOOSER
    //robot_autonomo_pruebaCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    // PARA PONER EL COMMAND GROUP EN OPCION PARA AUTONOMO
    //if(robot_autonomo_pruebaCommand != null){
    //  robot_autonomo_pruebaCommand.start();
    //}
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    main_intake_discosCommand = new Robot_Intake_Discos_Move();
    robot_chasisCommand= new Robot_Chasis_Maindrive();
    robot_elevadorCommand = new Robot_Elevador_Move();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    robot_chasisCommand.start();
    main_intake_discosCommand.start();
    robot_elevadorCommand.start();
    
    
  //Compresor activo en teleoperado
  /* 
   if (RobotMap.compresor.getPressureSwitchValue()){

      RobotMap.compresor.start();

    }else{
      
    }
  */

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
