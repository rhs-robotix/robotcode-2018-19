package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class firsthardware
{
    
    public DcMotor dBackLeft = null;
    public DcMotor dBackRight = null;
    public DcMotor dFrontLeft = null;
    public DcMotor dFrontRight = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public firsthardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        drivemotor  = hwMap.get(DcMotor.class, "drivemotor");
        /*drivemotor2  = hwMap.get(DcMotor.class, "drivemotor2");
        drivemotor3  = hwMap.get(DcMotor.class, "drivemotor3");
        drivemotor4  = hwMap.get(DcMotor.class, "drivemotor4");
        drivemotor5  = hwMap.get(DcMotor.class, "drivemotor5");
        drivemotor6  = hwMap.get(DcMotor.class, "drivemotor6");*/
        //myServo = hwMap.get(Servo.class, "myServo");


        // Set all motors to zero power
        drivemotor.setPower(0);
        /*drivemotor2.setPower(0);
        drivemotor3.setPower(0);
        drivemotor4.setPower(0);
        drivemotor5.setPower(0);
        drivemotor6.setPower(0);*/


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        drivemotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
 }
