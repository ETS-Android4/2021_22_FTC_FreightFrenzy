package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_IntakeTest;
import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_Lift;

// name this opMode and determine a group
@TeleOp(name="LiftEncoderTest", group="Test?")
public class LiftEncoderTest extends OpMode{

    /* Declare OpMode members. */
    HardwareMap_Lift robot       = new  HardwareMap_Lift();

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

        boolean isButtonB = gamepad1.b;
        boolean isButtonA = gamepad1.a;
        boolean isButtonX = gamepad1.x;
        boolean isButtonY = gamepad1.y;

        double speed = 0.5;

        if (isButtonA) {
            robot.lift.setPower(speed);
            telemetry.addData("Button","A");
            //A is retract
        } else if (isButtonB) {
            robot.lift.setPower(-speed);
            telemetry.addData("Button","B");
            //B is extend
        } else if (isButtonX) {
            robot.lift.setPower(1);
            telemetry.addData("Button","X");
            //X is retract, but with full power

        }  else if (isButtonY) {
            robot.lift.setPower(-1);
            telemetry.addData("Button","X");
            //X is retract, but with full power

        }else {
            telemetry.addData("Button","None");
            robot.lift.setPower(0);
        }
        telemetry.addData("Lift Position",String.format("%7d", robot.lift.getCurrentPosition()));
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
