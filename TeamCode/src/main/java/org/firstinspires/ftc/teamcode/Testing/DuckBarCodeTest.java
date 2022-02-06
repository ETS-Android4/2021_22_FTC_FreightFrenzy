

package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;


import java.util.List;

@Autonomous(name = "Duck Bar Code Test", group = "Test")

public class DuckBarCodeTest extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {"Ball", "Cube", "Duck", "Marker"};
    private static final String VUFORIA_KEY = "ARoZitn/////AAABmZ6FX2EMrUKzj0gbg/slFZeCJINyTVmTiYubXSQne+811U577IumBsFcWUorKfsiJLZLvq/p5zEcqWGczDMB4VQ3QNZyGjs5ncTkAaUxf+/BfZySUP598FFzJgFNk3rGNus2o83yX7ulRiXzicrKx+f+p2uNCjCGhY6c1BS+MtYn6Zu6n3ShX5WQ85qqGkcJHiURiaMEOfCHoRyAuITKcXNNR0YpaRr6GQF3npuu1IJMUv1QYmYqKGQSlYIZhmnYDSTakUoWj2bylDAxqFxN5g9/vupDn2nk49njysmiB0LrkIhaLkcGxlQ89pgjtF1DBMN+PUoHpJgspzY57oS13MT9pcDJxu+5xpFHrd6jKIgG";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    public String label;
    public boolean marker;

    private static List<Recognition> tfodRecogntions;

    public void runOpMode() throws InterruptedException {

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1, 16.0 / 9.0);
        }

        sleep(500);

        tfodRecogntions = tfod.getUpdatedRecognitions();

        for (Recognition recognition : tfodRecogntions) {
            telemetry.addData("Label: ", recognition.getLabel());
            if (recognition.getLabel().equals("Marker")) {
                marker = true;
            } else {
                marker = false;
            }
            if (!marker) {
                if (recognition.getLeft() < 135) {
                    label = "LEFT";
                } else if (recognition.getLeft() >= 135 && recognition.getLeft() <= 386) {
                    label = "MIDDLE";
                } else if (recognition.getLeft() > 386) {
                    label = "RIGHT";
                } else {
                    label = "None";
                }
            }
        }

        telemetry.addData("Element: ", label);
        telemetry.update();

        while (!isStarted()) {
            sleep(500);

            tfodRecogntions = tfod.getUpdatedRecognitions();

            for (Recognition recognition : tfodRecogntions) {
                telemetry.addData("Label: ", recognition.getLabel());
                if (recognition.getLabel().equals("Duck")) {
                    marker = true;
                } else {
                    marker = false;
                }
                if (!marker) {
                    if (recognition.getLeft() < 135) {
                        label = "LEFT";
                    } else if (recognition.getLeft() >= 135 && recognition.getLeft() <= 386) {
                        label = "MIDDLE";
                    } else if (recognition.getLeft() > 386) {
                        label = "RIGHT";
                    } else {
                        label = "None";
                    }
                }
            }

            telemetry.addData("Element: ", label);
            telemetry.update();
        }

        waitForStart();


        if(label == null || label.equals("RIGHT")) {
            // Enter in what do here

        }else if(label.equals("LEFT")){

            // Enter in what do here

        }
        else if(label.equals("MIDDLE")){
            // Enter in what do here
        }

    }

    private void initVuforia() {

        //Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.3f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 800;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}

