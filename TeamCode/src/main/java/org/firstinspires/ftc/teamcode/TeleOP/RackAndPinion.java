package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareMap.HardwareMap_CompetitionBot;

// name this OpMode and determine a group
@TeleOp (name="RackAndPinion", group="TeleOP")
public class RackAndPinion extends OpMode {

    /* Declare OpMode members. */
    HardwareMap_CompetitionBot robot       = new HardwareMap_CompetitionBot();

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

        boolean isButtonB = gamepad2.b;
        boolean isButtonA = gamepad2.a;
        boolean isButtonX = gamepad2.x;
        boolean isButtonY = gamepad2.y;

        boolean isButtonLB = gamepad2.left_bumper;
        boolean isButtonRB = gamepad2.right_bumper;

        boolean isButtonDU = gamepad2.dpad_up;
        boolean isButtonDR = gamepad2.dpad_right;
        boolean isButtonDL = gamepad2.dpad_left;
        boolean isButtonDD = gamepad2.dpad_down;

        boolean isBDU = gamepad1.dpad_up;
        boolean isBDR = gamepad1.dpad_right;
        boolean isBDL = gamepad1.dpad_left;
        boolean isBDD = gamepad1.dpad_down;

        double speed = 0.5;

        if (isButtonRB) {
            robot.lift.setPower(1);
            telemetry.addData("Button","RB");
            //A is retract
        } else if (isButtonLB) {
            robot.lift.setPower(-1);
            telemetry.addData("Button","LB");
            //A is retract
        }else {
            telemetry.addData("Button","None");
            robot.lift.setPower(0);
        }



        if (isButtonA) {
            robot.leftIntake.setPower(1);
            robot.rightIntake.setPower(1);
            telemetry.addData("Button","A");
            //A is retract
        } else if (isButtonB) {
            robot.leftIntake.setPower(1);
            robot.rightIntake.setPower(1);
            telemetry.addData("Button","B");
            //B is extend
        } else if (isButtonX) {
            robot.leftIntake.setPower(-1);
            robot.rightIntake.setPower(-1);
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

        if (isButtonDU) {
            robot.duckMotor.setPower(speed);
            telemetry.addData("Button","DU");
            //A is retract
        } else if (isButtonDD) {
            robot.duckMotor.setPower(-speed);
            telemetry.addData("Button","DD");
            //B is extend
        } else if (isButtonDR) {
            robot.duckMotor.setPower(1);
            telemetry.addData("Button","DR");
            //X is retract, but with full power

        }  else if (isButtonDL) {
            robot.duckMotor.setPower(-1);
            telemetry.addData("Button","DL");
            //X is retract, but with full power
        }else {
            telemetry.addData("Button","None");
            robot.duckMotor.setPower(0);
        }

        if (isBDU) {
            robot.duckMotor.setPower(speed);
            telemetry.addData("Button","DU");
            //A is retract
        } else if (isBDD) {
            robot.duckMotor.setPower(-speed);
            telemetry.addData("Button","DD");
            //B is extend
        } else if (isBDR) {
            robot.duckMotor.setPower(1);
            telemetry.addData("Button","DR");
            //X is retract, but with full power

        }  else if (isBDL) {
            robot.duckMotor.setPower(-1);
            telemetry.addData("Button","DL");
            //X is retract, but with full power
        }else {
            telemetry.addData("Button","None");
            robot.duckMotor.setPower(0);
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