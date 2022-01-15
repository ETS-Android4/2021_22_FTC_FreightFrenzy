package org.firstinspires.ftc.teamcode.HardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * This is NOT an opmode.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * WAGS:  Naming convention is camel case!
 *
 *          front
 *    (LF)--------(RF)
 *    |    robot   |
 *   (LB)--------(RB)
 *        back
 *
 * Motor channel:  Left Front (LF) drive motor:        "leftFront"
 * Motor channel:  Right Front (RF) drive motor:        "rightFront"
 * Motor channel:  Left Back (LB) drive motor:        "leftBack"
 * Motor channel:  Right Back (RB) drive motor:        "rightBack"
 */

public class HardwareMap_CompetitionBot
{
    /* Public OpMode members. */
    public DcMotor  leftFront   = null;
    public DcMotor  rightFront  = null;
    public DcMotor  leftBack   = null;
    public DcMotor  rightBack  = null;

    public DcMotor  leftIntake   = null;
    public DcMotor  rightIntake  = null;
    public DigitalChannel redLED;
    public DigitalChannel greenLED;
    public DcMotor  lift  = null;
    public DcMotor duckMotor = null;
    public BNO055IMU imu;
    public final double THRESHOLD = 3;

   //encoder value for levels 1 and 2 of shipping hub
   public int level1 = 1805;
   public int level2 = 4461;
   public int down = 0;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMap_CompetitionBot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFront  = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack  = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        duckMotor = hwMap.get(DcMotor.class, "duckMotor");

        leftFront.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightFront.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        leftBack.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightBack.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        duckMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        duckMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        duckMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and Initialize Motors
        leftIntake  = hwMap.get(DcMotor.class, "leftIntake");
        rightIntake = hwMap.get(DcMotor.class, "rightIntake");
        duckMotor = hwMap.get(DcMotor.class, "duckMotor");

        lift = hwMap.get(DcMotor.class, "lift");

        leftIntake.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rightIntake.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        duckMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        //IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        //get and initialize IMU
        imu = hwMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);
        //end IMU
        lift.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftIntake.setPower(0);
        rightIntake.setPower(0);
        duckMotor.setPower(0);

        lift.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        duckMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        redLED = hwMap.get(DigitalChannel.class, "red");
        greenLED = hwMap.get(DigitalChannel.class, "green");

        // change LED mode from input to output
        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);
    }



}