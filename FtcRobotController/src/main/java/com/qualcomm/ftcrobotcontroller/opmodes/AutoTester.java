package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by FTC10267 on 11/21/2015.
 */
public class AutoTester extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor beltMotor;
    DcMotor winch;

    final static int ENCODER_CPR = 1440;     //Encoder Counts per Revolution
    final static double GEAR_RATIO = 2;      //Gear Ratio
    final static int WHEEL_DIAMETER = 4;     //Diameter of the wheel in inches
    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

    // Forward calcluations
    final static int forwardDistance = 48;          //Distance in inches to drive
    final static double forwardRotations = forwardDistance / CIRCUMFERENCE;
    final static double forwardCounts = ENCODER_CPR * forwardRotations * GEAR_RATIO;

    // 90 degree turn calcluations
    final static double turningDistance = 10;
    final static double turningRotations = turningDistance / CIRCUMFERENCE;
    final static double turningCounts = ENCODER_CPR * turningRotations * GEAR_RATIO;

    // Forward to mountain calculations
    final static int forwardToMntDistance = 30;
    final static double forwardToMntRotations = forwardToMntDistance / CIRCUMFERENCE;
    final static double forwardToMntCounts = ENCODER_CPR * forwardToMntRotations * GEAR_RATIO;

    int step;


    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        step = 1;
    }

    @Override
    public void loop() {

        switch (step) {
            case 1:
                leftMotor.setTargetPosition((int) turningCounts);
                rightMotor.setTargetPosition((int) -turningCounts);

                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                leftMotor.setPower(1.0);
                rightMotor.setPower(-1.0);

                if(leftMotor.getCurrentPosition() == turningCounts) {
                    leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

                    step++;
                }
                break;
            case 2:
                leftMotor.setTargetPosition((int) (forwardToMntCounts + turningCounts));
                rightMotor.setTargetPosition((int) (forwardToMntCounts - turningCounts));

                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode((DcMotorController.RunMode.RUN_TO_POSITION));

                leftMotor.setPower(1.0);
                rightMotor.setPower(1.0);

                break;

        }

        telemetry.addData("Left Position", leftMotor.getCurrentPosition());
        telemetry.addData("Right Position", rightMotor.getCurrentPosition());
    }
}
