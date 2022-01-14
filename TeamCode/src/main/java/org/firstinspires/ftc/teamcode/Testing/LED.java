package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_Led;
import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_Lift;

@TeleOp(name="LED", group="Test")

public class LED extends OpMode {



    /* Declare OpMode members. */
    HardwareMap_Led robot       = new  HardwareMap_Led();

    @Override
    public void init() {

        /* Initialize the har5dware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;

        telemetry.addData("Say", "Hello WAGS Driver!!");    //


    }
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    @Override
    public void loop() {


        
        boolean isButtonA = gamepad1.a;


        double speed = 0.5;

        if (isButtonA) {
            robot.greenLED.setState(true);
            robot.redLED.setState(false);
            telemetry.addData("Button","A");
            //A is retract
        }else {
            robot.greenLED.setState(false);
            robot.redLED.setState(true);
            telemetry.addData("Button","None");
        }
        //telemetry.addData("Lift Position",String.format("%7d", robot.lift.getCurrentPosition()));
        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Say", "Good Job Team! We have STOPPED!!");

    }
}
