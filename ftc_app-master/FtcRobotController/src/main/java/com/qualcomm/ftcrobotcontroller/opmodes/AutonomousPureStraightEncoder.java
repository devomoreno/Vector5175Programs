/*The purpose of this autonomous mode is to go straight*/

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import all hardware going to be used
public class AutonomousPureStraightEncoder extends OpMode {
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


    public AutonomousPureStraightEncoder() {
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

        Treadleft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        Treadright.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


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
        int currentLeftEncoderPosition = Treadleft.getCurrentPosition();
        int currentRightEncoderPosition = Treadright.getCurrentPosition();


        if (currentTime <= 10) {
            Treadleft.setPower(0);
            Treadright.setPower(0);

        }

        if (currentTime > 10.1) {
            Treadleft.setTargetPosition(18400);
            Treadright.setTargetPosition(18400);

            Treadleft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            Treadright.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        }
    }
}