package org.firstinspires.ftc.teamcode.HardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareMap_DuckWheelTest
{
    /* Public OpMode members. */
    public DcMotor  duckMotor   = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMap_DuckWheelTest(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        duckMotor  = hwMap.get(DcMotor.class, "duckMotor");

        duckMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        // Set all motors to zero power
        duckMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        duckMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Neverest 60:1 Motor

    }

}
