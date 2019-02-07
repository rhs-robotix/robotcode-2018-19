package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Driver Controlled", group = "test")
//@Disabled
public class DriverControlled extends OpMode {

    // create robot object
    Robot robot = new Robot();

    // code runs when driver hits INIT
    @Override
    public void init() {

        // initialize robot
        robot.init(hardwareMap);

        // tell driver robot is ready
        telemetry.addData("Status", "Ready");

        // set all motors to 0
        robot.dFront.setPower(0);
        robot.dBack.setPower(0);
        robot.dLeft.setPower(0);
        robot.dRight.setPower(0);
        robot.armMotor.setPower(0);

    }

    // code loops after driver hits INIT and before hits PLAY
    @Override
    public void init_loop() {

    }

    // code loops when driver hits PLAY
    @Override
    public void loop() {

        // drive velocities
        double velocityX = gamepad1.left_stick_x;
        double velocityY = gamepad1.left_stick_y;

        // turning
        double velocityTurn = gamepad1.right_stick_x;

        // set motor powers
        robot.dFront.setPower(-velocityX - velocityTurn);
        robot.dBack.setPower(velocityX - velocityTurn);
        robot.dLeft.setPower(velocityY - velocityTurn);
        robot.dRight.setPower(-velocityY - velocityTurn);
        robot.armMotor.setPower(gamepad2.left_stick_y);
    }

    // code runs when driver hits STOP
    @Override
    public void stop() {

        // set all motors to 0
        robot.dFront.setPower(0);
        robot.dBack.setPower(0);
        robot.dLeft.setPower(0);
        robot.dRight.setPower(0);
        robot.armMotor.setPower(0);

    }

}
