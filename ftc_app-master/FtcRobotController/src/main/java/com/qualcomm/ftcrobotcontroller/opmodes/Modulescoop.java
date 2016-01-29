package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class Modulescoop extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor motorRaise;
    Servo leftscoop;
    Servo rightscoop;

    final static double SCOOP_MIN_RANGE  = 0.00;
    final static double SCOOP_MAX_RANGE  = 1.00;

    double scoopDelta=0.005;
    double leftscoopPosition;
    double rightscoopPosition;


    public Modulescoop(){}

    @Override
            public void init(){
        leftscoop=hardwareMap.servo.get("scoopleft");
        rightscoop=hardwareMap.servo.get("scoopright");
        motorRaise=hardwareMap.dcMotor.get("raisescoop");
        leftscoop.setDirection(Servo.Direction.REVERSE);
        leftscoopPosition=1;
        rightscoopPosition=1;

        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override


            public void loop(){

        float up=gamepad2.left_trigger;
        float down=gamepad2.right_trigger;
        up = Range.clip(up, 0, 1);
        down=Range.clip(down, 0,1);
        up = (float)scaleInput(up);
        down =(float)scaleInput(down);

        motorRaise.setPower(up);



        if  (down>0){
            leftscoopPosition -= scoopDelta;
            rightscoopPosition -= scoopDelta;

        }
        if (up>0){
            motorRaise.setPower(scaleInput(up));
        }
        if (gamepad2.right_bumper) {
            leftscoopPosition += scoopDelta;
            rightscoopPosition += scoopDelta;
        }
        if (gamepad2.left_bumper){
            motorRaise.setPower(scaleInput(-down));
        }




        leftscoopPosition = Range.clip(leftscoopPosition, SCOOP_MIN_RANGE, SCOOP_MAX_RANGE);
        rightscoopPosition = Range.clip(rightscoopPosition, SCOOP_MIN_RANGE, SCOOP_MAX_RANGE);

        // write position values to the wrist and claw servo
        leftscoop.setPosition(leftscoopPosition);
        rightscoop.setPosition(rightscoopPosition);





    //set all the driver and gamepad options. this is where the program goes.
    }
    @Override
        public void stop(){
        //this is, to my knowledge all that is needed for this public void
    }
    //This is for the driving scale as far as this point it is ok without modification
    double scaleInput(double dVal)  {
        double[] scaleArray = {0.00, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
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
