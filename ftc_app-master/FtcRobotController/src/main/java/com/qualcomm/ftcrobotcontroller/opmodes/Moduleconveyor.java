package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//import all hardware going to be used
public class Moduleconveyor extends OpMode {
    //name Dcmotors and for purpose of the program
    //ex:  Dcmotor Greg
    DcMotor Trough;

    public Moduleconveyor() {
    }

    @Override
    public void init() {
        Trough = hardwareMap.dcMotor.get("Trough");

        //map items here and set rules ( reference any vector baseline or basic programs)

    }

    @Override
    public void loop() {
        if (gamepad2.left_trigger > 0) {
            Trough.setPower(1);
        }
        if (gamepad2.right_trigger > 0) {
            Trough.setPower(-1);
        }
        //set all the driver and gamepad options. this is where the program goes.
    }

    @Override
    public void stop() {
        //this is, to my knowledge all that is needed for this public void
    }
}
    //This is for the driving scale as far as this point it is ok without modification
