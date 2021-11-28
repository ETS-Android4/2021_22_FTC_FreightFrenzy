package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_Holonomic;
import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_RackAndPinion;

// name this OpMode and determine a group
@TeleOp (name="RackAndPinion", group="TeleOP")
public class RackAndPinion extends OpMode {

    /* Declare OpMode members. */
    HardwareMap_RackAndPinion robot       = new HardwareMap_RackAndPinion();

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


        double rStickX;
        double rStickY;
        double lStickX;
        double targetAngle;
        double mag1;
        double mag2;
        double rotationPower;
        double maxPower;
        double scaleDown;

        rStickX = gamepad1.right_stick_x;
        rStickY = -gamepad1.right_stick_y;
        lStickX = gamepad1.left_stick_x;

        targetAngle = (Math.atan2(rStickY,rStickX));

        rotationPower = -lStickX;
        mag1 = Math.sqrt(Math.pow(rStickX,2) + Math.pow(rStickY,2)) * (Math.sin(targetAngle + Math.PI / 4));
        mag2 = Math.sqrt(Math.pow(rStickX,2) + Math.pow(rStickY,2)) * (Math.sin(targetAngle - Math.PI / 4));

        maxPower = Math.max(Math.abs(mag1) +  Math.abs(rotationPower) , Math.abs(mag2) +  Math.abs(rotationPower)) + 0.15;
        scaleDown = 1.0;

        if (maxPower > 1)
            scaleDown = 1.0 / maxPower;


        robot.leftFront.setPower((mag2 - rotationPower) * scaleDown);
        robot.rightFront.setPower((mag1 + rotationPower) * scaleDown);
        robot.leftBack.setPower((mag1 - rotationPower) * scaleDown);
        robot.rightBack.setPower((mag2 + rotationPower) * scaleDown);

        boolean isButtonB = gamepad1.b;
        boolean isButtonA = gamepad1.a;
        boolean isButtonX = gamepad1.x;
        boolean isButtonY = gamepad1.y;

        boolean isButtonLB = gamepad1.left_bumper;
        boolean isButtonRB = gamepad1.right_bumper;

        double speed = 0.5;

        if (isButtonRB) {
            robot.lift.setPower(speed);
            telemetry.addData("Button","A");
            //A is retract
        } else if (isButtonLB) {
            robot.lift.setPower(-speed);
            telemetry.addData("Button","B");
            //B is extend
        }else {
            telemetry.addData("Button","None");
            robot.lift.setPower(0);
        }



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