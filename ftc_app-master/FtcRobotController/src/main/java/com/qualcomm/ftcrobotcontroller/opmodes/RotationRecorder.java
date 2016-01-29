//The purpose of this program is simply to show the number of rotations that the drive motor uses to accomplish a task.

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//import all hardware going to be used
public class RotationRecorder extends OpMode {
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


    final static double SCOOP_MIN_RANGE  = 0.00;
    final static double SCOOP_MAX_RANGE  = 1.00;

    double scoopDelta=0.005;
    double leftscoopPosition;
    double rightscoopPosition;


    public RotationRecorder(){}

    @Override
            public void init(){
        leftscoop=hardwareMap.servo.get("scoopleft");
        rightscoop=hardwareMap.servo.get("scoopright");
        motorRaise=hardwareMap.dcMotor.get("raisescoop");
        leftscoop.setDirection(Servo.Direction.REVERSE);
        leftscoopPosition=0;
        rightscoopPosition=0;
        Trough = hardwareMap.dcMotor.get("Trough");

        //Drive
        Treadleft=hardwareMap.dcMotor.get("LeftTread");
        Treadright=hardwareMap.dcMotor.get("RightTread");
        Treadleft.setDirection(DcMotor.Direction.REVERSE);
        Treadright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        Treadleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        Treadleft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        Treadright.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


        //Zombie Arm

        LinearActuator = hardwareMap.dcMotor.get("ZLinearActuator");
        BaseExtend = hardwareMap.dcMotor.get("ZBaseExtend");
        SecondExtend = hardwareMap.dcMotor.get("ZSecondExtend");
        BaseExtend.setDirection(DcMotor.Direction.REVERSE);
        LinearActuator.setDirection(DcMotor.Direction.REVERSE);



        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    @Override
    //This class allows for the easy access and reset of the motor encoder data
            public void loop(){

        if(gamepad1.a){
            Treadleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            Treadright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }

        if(gamepad1.y){
            Treadleft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            Treadright.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        float left=gamepad1.left_stick_y;
        float right=gamepad1.right_stick_y;

        right= Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);

        Treadleft.setPower(left);
        Treadright.setPower(right);
        telemetry.addData("Left", "Left Motor: " + Treadleft.getCurrentPosition());
        telemetry.addData("Right", "Right Motor: " + Treadright.getCurrentPosition());

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
