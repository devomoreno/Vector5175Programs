
//*******This is a comment. this is not part of the program, though it is here, obviously. something is made a comment by inserting "//" in front of a textline, just like the one found in the beginng of this line*********
package com.qualcomm.ftcrobotcontroller.opmodes;
//this just serves to tell where the script should look for all things referenced
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//anything that is going to be used hardware or technical term wise, it has to be imported
public class Vectorservobasic extends OpMode{
    //this is where the values and basic rules are established
            final static double servo_min=.1;
            final static double servo_max=.9;
    //final means it doesnt change. Static means if it changes the variable attatched to it will change throughout, and double referes to the double place value (.01,.02,.99, etc)
            double servoPosition;
    //declares that servoPosition is a double variable
            //double vs float: double refers to double values (.01,.02,.99, etc); while float refers to a gradual change in integers (.123232, .143353, .385839, etc)
                //DM Note: The value ranges have been shortened to a -1 to 1 range for all cases where range is necessary (i.e. motors, servos, sensors, etc)
            double servoDelta=.005;
    //this declares that the Delta( which refers to the greek symbol, delta, meaning change  will be a certain value. this particular change in servo is going to be a double value
			DcMotor motorRight;
			DcMotor motorLeft;
            Servo Greg;
    //this declares the name of the devices given, this is where the number of motors, servos and sensors are declared.
		public Vectorservobasic(){
        }
			@Override
			public void init() {
				motorRight = hardwareMap.dcMotor.get("motorRight");
				motorLeft = hardwareMap.dcMotor.get("motorLeft");
				motorLeft.setDirection(DcMotor.Direction.REVERSE);
                //this is for tank drive (notice the Reverse)
                //This area in general is meant for declaring what the name of an item above in the public class is refering to as well as what it is supposed to be named in the app when configuring

                Greg = hardwareMap.servo.get("Greg");
			}
			@Override
			public void loop() {
                float left = gamepad1.left_stick_y;
                float right = gamepad1.right_stick_y;
//this is the main program piece for tank drive

                right = Range.clip(right, -1, 1);
                left = Range.clip(left, -1, 1);
                //This limits the range of the motors movement, to agree with the new scale limits
                right = (float) scaleInput(right);
                left = (float) scaleInput(left);

                motorRight.setPower(right);
                motorLeft.setPower(left);

                if (gamepad1.left_bumper) {
                    // if the left bumper is pushed on gamepad1, increment the position of
                    // the arm servo.
                    servoPosition += servoDelta;
                }

                if (gamepad1.right_bumper) {
                    // if the right bumper is pushed on gamepad1, decrease the position of
                    // the arm servo.
                    servoPosition -= servoDelta;
                }

                servoPosition = Range.clip(servoPosition, servo_min, servo_max);
                    //This limits the range of the servos movement, to agree with the new scale limits
                Greg.setPosition(servoPosition);

                telemetry.addData("Text", "*** Your princess is in another castle***");
            }//adds text to the driver app

				@Override
				public void stop() {

				}

	/*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
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

					double dScale ;
					if (dVal < 0) {
						dScale = -scaleArray[index];
					} else {
						dScale = scaleArray[index];
					}

					return dScale;
				}

			}
