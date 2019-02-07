package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Collection Testing", group="test")
@Disabled
public class CollectionTesting extends OpMode {

    // create robot object
    Robot robot = new Robot();

    // code runs when driver hits INIT
    @Override
    public void init() {

        // initialize robot
        robot.init(hardwareMap);

        // tell driver robot is ready
        telemetry.addData("Status", "Ready");

    }

    // code loops after driver hits INIT and before hits PLAY
    @Override
    public void init_loop() {

    }

    // code loops when driver hits PLAY
    @Override
    public void loop() {

        double speed = gamepad1.left_stick_y;
        //robot.c_Servo1.setPower(speed);
        //robot.c_Servo2.setPower(-speed);


    }

    // code runs when driver hits STOP
    @Override
    public void stop() {

    }

}
