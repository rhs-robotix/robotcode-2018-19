package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Driver Controlled", group="test")
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

    }

    // code loops after driver hits INIT and before hits PLAY
    @Override
    public void init_loop() {

    }

    // code loops when driver hits PLAY
    @Override
    public void loop() {

       // double speed = gamepad1.left_stick_y;


        //Collection System

        //collection servos collect
        if(gamepad2.right_trigger == 1)
        {
            robot.c_Servo2.setPower(-1);
            robot.c_Servo1.setPower(1);
        }
        //collection motors dispense
        else if(gamepad2.left_trigger == 1)
        {
            robot.c_Servo2.setPower(1);
            robot.c_Servo1.setPower(-1);
        }
        else
        {
            robot.c_Servo2.setPower(0);
            robot.c_Servo1.setPower(0);
        }


        //Arm Movement
        robot.armMotor.setPower(gamepad2.left_stick_y);
        robot.upperArmMotor.setPower(gamepad2.right_stick_y);



        //Spool Motor
        robot.spoolMotor.setPower(gamepad2.left_stick_y);

        //Driving

        //move forward
        if(gamepad1.left_stick_y == -1)
        {
            robot.leftDriveMotor.setPower(1);
            robot.rightDriveMotor.setPower(1);
        }
        //move backwards
        else if(gamepad1.left_stick_y == 1)
        {
            robot.leftDriveMotor.setPower(-1);
            robot.rightDriveMotor.setPower(-1);
        }
        //turns right
        else if(gamepad1.left_stick_x == 1)
        {
        robot.rightDriveMotor.setPower(-1);
        robot.leftDriveMotor.setPower(1);
        }
        //turns left
        else if(gamepad1.left_stick_x == -1)
        {
            robot.rightDriveMotor.setPower(1);
            robot.leftDriveMotor.setPower(-1);

        }
        else
            {
                robot.rightDriveMotor.setPower(0);
                robot.leftDriveMotor.setPower(0);
            }
    }

    // code runs when driver hits STOP
    @Override
    public void stop() {

    }

}
