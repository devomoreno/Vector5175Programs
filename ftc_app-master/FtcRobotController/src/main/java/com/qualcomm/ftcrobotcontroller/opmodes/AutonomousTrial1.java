package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


//import all hardware going to be used
public class AutonomousTrial1 extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor Treadleft;
    DcMotor Treadright;

    public AutonomousTrial1(){}

    @Override
            public void init(){
        Treadleft=hardwareMap.dcMotor.get("LeftTread");
        Treadright=hardwareMap.dcMotor.get("RightTread");
        Treadright.setDirection(DcMotor.Direction.REVERSE);

        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override
            public void loop(){
        resetStartTime();


        long startTime = System.currentTimeMillis();
        while((System.currentTimeMillis()-startTime)>10000 && (System.currentTimeMillis()-startTime)<12000){
                      Treadleft.setPower(1);
        }             Treadright.setPower(1);

        while((System.currentTimeMillis()-startTime)>12001 && (System.currentTimeMillis()-startTime)<15000){
            Treadleft.setPower(-.5);
            Treadright.setPower(.5);
        }
       while ((System.currentTimeMillis()-startTime)>15001 && (System.currentTimeMillis()-startTime)<20000){
           Treadleft.setPower(.5);
           Treadright.setPower(.5);
       }


    //set all the driver and gamepad options. this is where the program goes.
    }
    @Override
        public void stop(){
        //this is, to my knowledge all that is needed for this public void
    }
    //This is for the driving scale as far as this point it is ok without modification

}
