package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class ContestTank2 extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor motorRaise;
    Servo leftscoop;
    Servo rightscoop;
    DcMotor Treadleft;
    DcMotor Treadright;
    DcMotor Trough;

    final static double SCOOP_MIN_RANGE  = 0.00;
    final static double SCOOP_MAX_RANGE  = 1.00;

    double scoopDelta=0.005;
    double leftscoopPosition;
    double rightscoopPosition;


    public ContestTank2(){}

    @Override
            public void init(){
        leftscoop=hardwareMap.servo.get("scoopleft");
        rightscoop=hardwareMap.servo.get("scoopright");
        motorRaise=hardwareMap.dcMotor.get("raisescoop");
        rightscoop.setDirection(Servo.Direction.REVERSE);
        leftscoopPosition=0;
        rightscoopPosition=0;
        Treadleft=hardwareMap.dcMotor.get("LeftTread");
        Treadright=hardwareMap.dcMotor.get("RightTread");
        Treadright.setDirection(DcMotor.Direction.REVERSE);
        Trough = hardwareMap.dcMotor.get("Trough");


        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override


            public void loop(){

        float lowerScoopArm=gamepad1.left_trigger;
        float lowerScoop=gamepad1.right_trigger;
        float left=gamepad1.left_stick_y;
        float right=gamepad1.right_stick_y;


        lowerScoopArm = Range.clip(lowerScoopArm, 0, 1);
        lowerScoop=Range.clip(lowerScoop, 0,1);
        right= Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        lowerScoopArm = (float)scaleInput(lowerScoopArm);
        lowerScoop =(float)scaleInput(lowerScoop);
        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);


        motorRaise.setPower(lowerScoopArm);
        Treadleft.setPower(left);
        Treadright.setPower(right);



        if  (lowerScoop>0){
            leftscoopPosition -= scoopDelta;
            rightscoopPosition -= scoopDelta;

        }
        if (lowerScoopArm>0){
            motorRaise.setPower(scaleInput(lowerScoopArm));
        }
        if (gamepad1.right_bumper) {
            leftscoopPosition += scoopDelta;
            rightscoopPosition += scoopDelta;
        }
        if (gamepad1.left_bumper){
            motorRaise.setPower(-.25);
        }
        while (gamepad2.left_trigger > 0) {
            Trough.setPower(1);
        }
        while (gamepad2.right_trigger > 0) {
            Trough.setPower(-1);
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
