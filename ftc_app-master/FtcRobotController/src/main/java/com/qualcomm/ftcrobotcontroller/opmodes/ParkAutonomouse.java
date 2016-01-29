package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class ParkAutonomouse extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor motorRaise;
    Servo leftscoop;
    Servo rightscoop;
    DcMotor Treadleft;
    DcMotor Treadright;
    DcMotor Trough;

    final static double SCOOP_MIN_RANGE = 0.00;
    final static double SCOOP_MAX_RANGE = 1.00;

    double scoopDelta = 0.005;
    double leftscoopPosition;
    double rightscoopPosition;


    public ParkAutonomouse() {
    }

    @Override
    public void init() {
        leftscoop = hardwareMap.servo.get("scoopleft");
        rightscoop = hardwareMap.servo.get("scoopright");
        motorRaise = hardwareMap.dcMotor.get("raisescoop");
        rightscoop.setDirection(Servo.Direction.REVERSE);
        leftscoopPosition = 0;
        rightscoopPosition = 0;
        Treadleft = hardwareMap.dcMotor.get("LeftTread");
        Treadright = hardwareMap.dcMotor.get("RightTread");
        Treadright.setDirection(DcMotor.Direction.REVERSE);
        Trough = hardwareMap.dcMotor.get("Trough");

        resetStartTime();


        //map items here and set rules ( reference any vector baseline or basic programs)

    }

    @Override


    public void loop() {

        while (time < 30) {
            while (10 < time && time < 20) {
                Treadright.setPower(.9);
                Treadleft.setPower(1);
            }
            while (20 < time && time < 30) {
                Treadleft.setPower(.6);
                Treadright.setPower(.55);
            }
        }
        if (time == 30) {
            stop();
        }

        leftscoopPosition = Range.clip(leftscoopPosition, SCOOP_MIN_RANGE, SCOOP_MAX_RANGE);
        rightscoopPosition = Range.clip(rightscoopPosition, SCOOP_MIN_RANGE, SCOOP_MAX_RANGE);

        // write position values to the wrist and claw servo
        leftscoop.setPosition(leftscoopPosition);
        rightscoop.setPosition(rightscoopPosition);


        //set all the driver and gamepad options. this is where the program goes.
    }
}