package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="LED", group="Test")

public class LED extends LinearOpMode {
    private DigitalChannel redLED;
    private DigitalChannel greenLED;


    @Override
    public void runOpMode() {
        // Get the LED colors and touch sensor from the hardwaremap
        redLED = hardwareMap.get(DigitalChannel.class, "red");
        greenLED = hardwareMap.get(DigitalChannel.class, "green");

        // Wait for the play button to be pressed
        waitForStart();

        // change LED mode from input to output
        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);

        boolean isButtonB = gamepad1.b;
        // Loop while the Op Mode is running
        while (opModeIsActive()) {
            if (isButtonB){
                greenLED.setState(true);
                redLED.setState(false);

            } else {
                redLED.setState(true);
                greenLED.setState(false);
            }

        }
    }
}
