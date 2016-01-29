package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class RedRightAutonomous extends OpMode {
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


    double leftscoopPosition;
    double rightscoopPosition;


    public RedRightAutonomous() {
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

       while(time<30){
           while(10<=time && time<=11){
               Treadright.setPower(.5);
               Treadleft.setPower(.5);
           }
           while(12<=time && time<=14){
               Treadleft.setPower(-.5);
               Treadright.setPower(.5);
           }
           while(14<=time && time<=17){
               Treadleft.setPower(.5);
               Treadright.setPower(.5);
           }
           while(17<=time && time<=18){
               Treadleft.setPower(-.5);
               Treadright.setPower(.5);
           }
           while(18<=time && time<=30){
               Treadleft.setPower(0);
               Treadright.setPower(0);
           }
       }
        if (time==30){
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