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

        // tell robot driver is ready
        telemetry.addData("Status", "Ready");

        // set all motors to 0
        robot.stopMotors();

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

        // lift spool motor
        // use right bumper and right stick on gamepad2
        if (gamepad2.right_bumper) {
            robot.liftSpool.setPower(gamepad2.right_stick_y);
        } else {
            robot.liftSpool.setPower(0);
        }

        // arm motors
        // use right stick for spool, left stick for arm on gamepad2
        if (!gamepad2.right_bumper) {
            robot.armMotor.setPower(.2 * gamepad2.left_stick_y);
            robot.armSpool.setPower(-gamepad2.right_stick_y);
        } else {
            robot.armMotor.setPower(0);
            robot.armSpool.setPower(0);
        }


    }

    // code runs when driver hits STOP
    @Override
    public void stop() {

        // set all motors to 0
        robot.stopMotors();

    }

}
