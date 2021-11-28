package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_IntakeTest;

// name this opMode and determine a group
@TeleOp(name="Intake Test", group="Test?")
public class IntakeTest extends OpMode{

    /* Declare OpMode members. */
    HardwareMap_IntakeTest robot       = new HardwareMap_IntakeTest();

    @Override
    public void init() {

        /* Initialize the hardware variables.
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
            robot.leftIntake.setPower(speed);
            robot.rightIntake.setPower(speed);
            telemetry.addData("Button","A");
            //A is retract
        } else if (isButtonB) {
            robot.leftIntake.setPower(-speed);
            robot.rightIntake.setPower(-speed);
            telemetry.addData("Button","B");
            //B is extend
        } else if (isButtonX) {
            robot.leftIntake.setPower(1);
            robot.rightIntake.setPower(1);
            telemetry.addData("Button","X");
            //X is retract, but with full power

        }  else if (isButtonY) {
            robot.leftIntake.setPower(-1);
            robot.rightIntake.setPower(-1);
            telemetry.addData("Button","X");
            //X is retract, but with full power

        }else {
            telemetry.addData("Button","None");
            robot.leftIntake.setPower(0);
            robot.rightIntake.setPower(0);
        }


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Say", "Good Job Team! We have STOPPED!!");

    }


}
