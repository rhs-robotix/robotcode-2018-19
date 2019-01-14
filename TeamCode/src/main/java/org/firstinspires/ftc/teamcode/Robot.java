package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {

    // Servos for collection
    public CRServo c_Servo1 = null;
    public CRServo c_Servo2 = null;


    // map all servos and motors
    HardwareMap hwMap = null;
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        // Servos for collection
        c_Servo1 = getCRServo("c_Servo1");
        c_Servo2 = getCRServo("c_Servo2");

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
