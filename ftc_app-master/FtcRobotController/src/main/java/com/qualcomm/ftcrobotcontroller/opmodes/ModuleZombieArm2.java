package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
//import all hardware going to be used
public class ModuleZombieArm2 extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotorController MotorController;
    DcMotor LinearActuator;
    DcMotor BaseExtend;
    DcMotor SecondExtend;
    DcMotorController.DeviceMode devMode;
    DcMotorController MotorController2;
    public ModuleZombieArm2(){}

    @Override
            public void init(){
        MotorController = hardwareMap.dcMotorController.get("Motorola");
        LinearActuator = hardwareMap.dcMotor.get("ZLinearActuator");
        BaseExtend = hardwareMap.dcMotor.get("ZBaseExtend");
        SecondExtend = hardwareMap.dcMotor.get("ZSecondExtend");
        MotorController2 = hardwareMap.dcMotorController.get("Motorola2");
        BaseExtend.setDirection(DcMotor.Direction.REVERSE);
        LinearActuator.setDirection(DcMotor.Direction.REVERSE);

        //map items here and set rules ( reference any vector baseline or basic programs)

    }
    public void init_loop() {

        devMode = DcMotorController.DeviceMode.WRITE_ONLY;


        //motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // set the mode
        // Nxt devices start up in "write" mode by default, so no need to switch device modes here.
        LinearActuator.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BaseExtend.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        SecondExtend.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }
    @Override
            public void loop(){

        float motorPower=gamepad2.left_stick_y;
        while(gamepad2.left_stick_y!=0) {


            motorPower = Range.clip(motorPower, -1, 1);


            motorPower = (float) scaleInput(motorPower);


            LinearActuator.setPower(motorPower);

        }
        while(gamepad2.left_stick_y==0){
            LinearActuator.setPower(0);
        }

     while(gamepad2.left_trigger != 0){
            BaseExtend.setPower(-.5);
            SecondExtend.setPower(-.5);
        }
        while(gamepad2.right_trigger != 0){
            BaseExtend.setPower (.5);
            SecondExtend.setPower (.5);
        }

        while(gamepad2.left_trigger == 0 && gamepad2.right_trigger ==0){
            BaseExtend.setPower (0);
            SecondExtend.setPower (0);
        }


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
