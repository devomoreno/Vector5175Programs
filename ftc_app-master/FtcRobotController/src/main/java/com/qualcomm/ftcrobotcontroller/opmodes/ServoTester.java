package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class ServoTester extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    Servo Testservo;
    final static double servo_min=.1;
    final static double servo_max=.9;
    double servoPosition;
    double servoDelta=.005;

    public ServoTester(){}


    @Override
            public void init(){
        Testservo = hardwareMap.servo.get("Servo");

        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override
            public void loop(){
        long startTime = System.currentTimeMillis();
        while((System.currentTimeMillis()-startTime)>0 && (System.currentTimeMillis()-startTime)<5000){
            servoPosition += servoDelta;
        }
        while((System.currentTimeMillis()-startTime)>5001 && (System.currentTimeMillis()-startTime)<10000){
            servoPosition -= servoDelta;
        }
        servoPosition = Range.clip(servoPosition, servo_min, servo_max);
        Testservo.setPosition(servoPosition);
    //set all the driver and gamepad options. this is where the program goes.
    }
    @Override
        public void stop(){
        //this is, to my knowledge all that is needed for this public void
    }
    //This is for the driving scale as far as this point it is ok without modification

}
