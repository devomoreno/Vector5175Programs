package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class Modulerescuearm extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg


    DcMotor strechyarm;
    DcMotor extendofingers;


    public Modulerescuearm (){}

    @Override
            public void init(){
        strechyarm=hardwareMap.dcMotor.get("Deploy");
        extendofingers=hardwareMap.dcMotor.get("Pull");
        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override
            public void loop(){
    //set all the driver and gamepad options. this is where the program goes.
        float deploy=gamepad2.left_stick_y;
        float pull=gamepad2.right_stick_y;

        deploy=Range.clip(deploy,-1,1);
        pull=Range.clip(pull,-1,1);

        deploy = (float)scaleInput(deploy);
        pull =  (float)scaleInput(pull);

        strechyarm.setPower(deploy);
        extendofingers.setPower(pull);
    }
    @Override
        public void stop(){
        //this is, to my knowledge all that is needed for this public void
    }
    //This is for the driving scale as far as this point it is ok without modification
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }
}
