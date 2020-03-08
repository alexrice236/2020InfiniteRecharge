/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Commands.AutoDriveForward;
import frc.robot.Commands.AutoTurn;
import frc.robot.Commands.StraightForwardScoring;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Climber;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.PIDDrivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 
  public static OI oi;

  public static PIDDrivetrain drivetrain;
  public static Intake intake;
  public static Climber climber;
  public static Arm arm;


  public static DigitalInput upperLimit;
  public static DigitalInput lowerLimit;

  public static double autoSetpoint;


  public static UsbCamera frontCam;
  public static UsbCamera backCam;
  public static final int IMG_WIDTH = 320;
  public static final int IMG_HEIGHT = 240;

  public static AHRS gyro;

  Command autonomousCommand;
  SendableChooser<Command> chooser;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    oi = new OI();

    drivetrain = new PIDDrivetrain();

    intake = new Intake();

    climber = new Climber();

    arm = new Arm();


    VideoMode videoMode = new VideoMode(1, IMG_WIDTH, IMG_HEIGHT, 30);
   
    frontCam = CameraServer.getInstance().startAutomaticCapture(RobotMap.frontCamera);
    frontCam.setResolution(IMG_WIDTH, IMG_HEIGHT);
    frontCam.setExposureAuto();

    CvSink cvSink1 = new CvSink("Cam_Front");
    cvSink1.setSource(frontCam);

    CvSource output1 = new CvSource("Front_Camera", PixelFormat.kMJPEG, IMG_WIDTH, IMG_HEIGHT, 50);

    MjpegServer mjServer = new MjpegServer("Front_Camera_Server", 7072);
    mjServer.setSource(output1);

    backCam = CameraServer.getInstance().startAutomaticCapture(RobotMap.backCamera);
    backCam.setResolution(IMG_WIDTH, IMG_HEIGHT);
    backCam.setExposureAuto();

    CvSink cvSink2 = new CvSink("Cam_Back");
    cvSink2.setSource(backCam);

    CvSource output2 = new CvSource("Back_Camera", PixelFormat.kMJPEG, IMG_WIDTH, IMG_HEIGHT, 50);

    MjpegServer mjServer2 = new MjpegServer("Back_Camera_Server", 7072);
    mjServer2.setSource(output2);

    upperLimit = new DigitalInput(RobotMap.upperLimit);
    lowerLimit = new DigitalInput(RobotMap.lowerLimit);

    gyro = new AHRS(SPI.Port.kMXP);

    chooser = new SendableChooser<>();
    chooser.addOption("DriveForward", new StraightForwardScoring());
    chooser.addOption("Turn90", new AutoTurn(90));
    SmartDashboard.putData("Initial Chooser", chooser);



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

    SmartDashboard.putBoolean("ArmUP", upperLimit.get());
    SmartDashboard.putBoolean("ArmDOWN", lowerLimit.get());

    SmartDashboard.putNumber("leftEncoder", Robot.drivetrain.getLeftEncoderPosition());
    SmartDashboard.putNumber("rightEncoder", Robot.drivetrain.getRightEncoderPosition());

    SmartDashboard.putNumber("GyroAngle", gyro.getAngle());

    if(gyro.getAngle()>360){
      gyro.reset();
    }else if(gyro.getAngle() < -360){
      gyro.reset();
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();
    autonomousCommand.start();
  }
  

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
