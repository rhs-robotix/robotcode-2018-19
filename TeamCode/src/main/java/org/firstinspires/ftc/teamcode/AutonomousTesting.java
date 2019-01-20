package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name="Autonomous Testing", group="test")
//@Disabled
public class AutonomousTesting extends LinearOpMode {

    // create instance of robot class
    Robot robot = new Robot();

    // create ElapsedTime instance to keep track of time
    private ElapsedTime runtime = new ElapsedTime();

    // stuff for object detection
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AXiRIVP/////AAABmd4YxoHMfEAluRCJBzglbshaaZHlQ95Kj0WAG03kdOhp60S6qSuD8eEpfT4xIM1Dj11r/RYs9smWcMkb+ig/ce1F/oneJaBkI1wwWAbmYOXtF84VZRzX2k065jgswhKJosqTS6HDCdZgejgiy2HMVRvBLzaYoAVjlVW6vPfFEyjXnR+b7uRd9Y3mcwhUKQIbQJcnwg4OHh3s2TuqF0HxG/w+awwnMcgOd9EeKlOPerh5heiHD3YX1qipqU47O+91wN4hFkVSX8hhUYbWhGntTd2ZqqpqIJXn+MtcxeVpXP28J6+D4gldxG/iSossKhBwd0Eup3DVpn3UtFjvxEC09FuKWQ1KnQ8bBNmfPqWNarYV";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;



    // this method is run when user hits start
    @Override
    public void runOpMode() {

        // initialize object detection and wait for start
        initVuforia();
        initTfod();
        waitForStart();

        // run object detection for 5 seconds
        double odTime = 0.0;
        String goldLocation = "idk";
        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (runtime.seconds() < 5) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getTop();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getTop();
                                } else {
                                    silverMineral2X = (int) recognition.getTop();
                                }
                            }
                            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    goldLocation = "left";
                                    telemetry.addData("Gold Mineral Position", "Left");
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    goldLocation = "right";
                                    telemetry.addData("Gold Mineral Position", "Right");
                                } else {
                                    goldLocation = "center";
                                    telemetry.addData("Gold Mineral Position", "Center");
                                }
                            }
                        }
                        telemetry.update();
                    }
                }
            }
        }


        if (opModeIsActive()) {
            if (!goldLocation.equals("idk")) {

                double offset = runtime.seconds();
                while (runtime.seconds() - offset < .2) {
                    if (goldLocation.equals("right")) {
                        robot.leftDriveMotor.setPower(.5);
                        robot.rightDriveMotor.setPower(-1);
                    } else if (goldLocation.equals("left")) {
                        robot.leftDriveMotor.setPower(-.5);
                        robot.rightDriveMotor.setPower(1);
                    }
                }


                offset = runtime.seconds();
                while (runtime.seconds() - offset < 3) {
                    robot.leftDriveMotor.setPower(.5); // this side is working
                    robot.rightDriveMotor.setPower(1); // this side is broken so its set to full speed to match left side
                }

            }
        }


    }



    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
