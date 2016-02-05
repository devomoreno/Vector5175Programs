package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import all hardware going to be used
public class StraightAutonomousCaseTrials extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor Trough;
    DcMotor motorRaise;
    Servo leftscoop;
    Servo rightscoop;

    //Drive
    DcMotor Treadleft;
    DcMotor Treadright;

    //Zombie Arm

    DcMotor LinearActuator;
    DcMotor BaseExtend;
    DcMotor SecondExtend;

    ElapsedTime counter;


    final static double SCOOP_MIN_RANGE = 0.00;
    final static double SCOOP_MAX_RANGE = 1.00;

    double scoopDelta = 0.005;
    double leftscoopPosition;
    double rightscoopPosition;


    public StraightAutonomousCaseTrials() {
    }

    @Override
    public void init() {
        //scoop and trough
        leftscoop = hardwareMap.servo.get("scoopleft");
        rightscoop = hardwareMap.servo.get("scoopright");
        motorRaise = hardwareMap.dcMotor.get("raisescoop");
        leftscoop.setDirection(Servo.Direction.REVERSE);
        leftscoopPosition = 0;
        rightscoopPosition = 0;
        Trough = hardwareMap.dcMotor.get("Trough");

        //Drive
        Treadleft = hardwareMap.dcMotor.get("LeftTread");
        Treadright = hardwareMap.dcMotor.get("RightTread");
        Treadright.setDirection(DcMotor.Direction.REVERSE);

        //Zombie Arm

        LinearActuator = hardwareMap.dcMotor.get("ZLinearActuator");
        BaseExtend = hardwareMap.dcMotor.get("ZBaseExtend");
        SecondExtend = hardwareMap.dcMotor.get("ZSecondExtend");
        BaseExtend.setDirection(DcMotor.Direction.REVERSE);
        LinearActuator.setDirection(DcMotor.Direction.REVERSE);

        counter = new ElapsedTime();


        //map items here and set rules ( reference any vector baseline or basic programs)

    }

    @Override
    public void loop() {



        double currentTime = counter.time();

        final double TIME_WAIT = 1000;
        final double END_TIME = 3000;


        if (currentTime < TIME_WAIT) {
            Treadleft.setPower(.2d);
            Treadright.setPower(.2d);
        }
        if (currentTime > TIME_WAIT && currentTime > END_TIME) {
            Treadleft.setPower(0);
            Treadright.setPower(0);

        }
        if (currentTime >= END_TIME) {
            Treadleft.setPower(-.2d);
            Treadright.setPower(-.2d);
        }

        telemetry.addData("Count", "Time: " + counter.time());
        //set all the driver and gamepad options. this is where the program goes.
    }

    @Override
    public void stop() {
        //this is, to my knowledge all that is needed for this public void
    }
    //This is for the driving scale as far as this point it is ok without modification
}