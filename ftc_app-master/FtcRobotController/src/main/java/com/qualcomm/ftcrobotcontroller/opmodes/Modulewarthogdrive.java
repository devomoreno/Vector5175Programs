package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class Modulewarthogdrive extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor Treadleft;
    DcMotor Treadright;
    public Modulewarthogdrive(){}

    @Override
            public void init(){
        Treadleft=hardwareMap.dcMotor.get("LeftTread");
        Treadright=hardwareMap.dcMotor.get("RightTread");
        Treadleft.setDirection(DcMotor.Direction.REVERSE);

        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override
            public void loop(){
        float throttle = -gamepad1.right_stick_y;
        float direction = gamepad1.right_stick_x;
        float right = throttle - direction;
        float left = throttle + direction;

        right= Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);

        Treadleft.setPower(left);
        Treadright.setPower(right);
    //set all the driver and gamepad options. this is where the program goes.
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
