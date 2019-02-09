package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {

    // drive motors
    public DcMotor dFront = null;
    public DcMotor dBack = null;
    public DcMotor dLeft = null;
    public DcMotor dRight = null;
    public DcMotor armMotor = null;
    public DcMotor armSpool = null;
    public DcMotor liftSpool = null;

    // map all servos and motors
    HardwareMap hwMap = null;
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // drive motors
        dFront = getMotor("dFront");
        dBack = getMotor("dBack");
        dLeft = getMotor("dLeft");
        dRight = getMotor("dRight");
        armMotor = getMotor("armMotor");
        armSpool = getMotor("armSpool");
        liftSpool = getMotor("liftSpool");

    }


    // shorten lines for getting servo and motor references
    public Servo getServo(String servoName) {
        return hwMap.get(Servo.class, servoName);
    }

    public CRServo getCRServo(String servoName) {
        return hwMap.get(CRServo.class, servoName);
    }

    public DcMotor getMotor(String motorName) {
        return hwMap.get(DcMotor.class, motorName);
    }
}
