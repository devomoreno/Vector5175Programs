package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//import all hardware going to be used
public class ModuleZombieArm extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor ExtendNoControl;
    DcMotor SecondStageExtend;
    DcMotor LinearAxuator;
    DcMotor SecondStageRetract;
    //DcMotor FirstStageExtend;
    //DcMotor FirstStageRetract;

    public ModuleZombieArm(){}

    @Override
            public void init(){
        ExtendNoControl=hardwareMap.dcMotor.get("FirstExtend");
        SecondStageExtend=hardwareMap.dcMotor.get("SecondStage");
        LinearAxuator=hardwareMap.dcMotor.get("LinearAxuator");
        SecondStageRetract = hardwareMap.dcMotor.get("SecondStage");


        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override
            public void loop(){

        if (gamepad2.y) {
             resetStartTime();
            while (time<3){
                ExtendNoControl.setPower(.5);
            }
            if(gamepad2.left_stick_y>0){
                SecondStageExtend.setPower(.5);
            }
            if(gamepad2.left_stick_y<0){
                SecondStageExtend.setPower(-.5);
            }
            if (gamepad2.right_stick_y<0){
                LinearAxuator.setPower(-.5);
            }
            if (gamepad2.right_stick_y>0){
                LinearAxuator.setPower(.5);
            }
         }
        if(gamepad2.a) {
            resetStartTime();
            while (time < 3) {
                SecondStageExtend.setPower(-.5);
                LinearAxuator.setPower(-.5);
            }
            if (gamepad2.left_stick_y > 0) {
                SecondStageRetract.setPower(.5);
            }
            if (gamepad2.left_stick_y < 0) {
                LinearAxuator.setPower(-.5);
            }
            if (gamepad2.right_stick_y < 0) {
                ExtendNoControl.setPower(-.5);
            }
            if (gamepad2.right_stick_y > 0) {
                ExtendNoControl.setPower(.5);
            }
        }
    //set all the driver and gamepad options. this is where the program goes.
    }
        @Override

     public void stop(){
        }
    }
//There is a problem that I will have to consult to someone about. The problem occurs when you want the retraction of the linear
//actuator, assuming you have the bar in the hook. The problem occurs that you have a collision of the two motors if you have the
//bottom motor retract first. I have taken that into account and thought I fixed it by having the linear actuator take the arm to a
//safe position to pull up and (hopefully not causing too much confusion) switched the right stick, which is the linear actuator if
//you press Y, with the first stage extension. So the program Technically takes the arm to a safe position to pull, but it is not in
// any way convinient, due to the fact that if you linear actuate onto the bar and press A (which is in fact required) to start the pull
// Then the linear actuator, in theory would simply take off the bar (or pull the robot into an uncomfortable position {depending on the
//accuracy of my current knowledge}) and retract safely based on the drivers control (as the rest would be driver control).