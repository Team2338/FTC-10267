package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Eddie Ho on 11/21/2015.
 */
public class AutoStateMachine extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor beltMotor;
    DcMotor winch;

    final static int ENCODER_CPR = 1440;     //Encoder Counts per Revolution
    final static double GEAR_RATIO = 3;      //Gear Ratio
    final static int WHEEL_DIAMETER = 4;     //Diameter of the wheel in inches
    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

    // Forward calcluations
    final static int forwardDistance = 48;          //Distance in inches to drive
    final static double forwardRotations = forwardDistance / CIRCUMFERENCE;
    final static double forwardCounts = ENCODER_CPR * forwardRotations * GEAR_RATIO;

    // 90 degree turn calcluations
    final static double turningDistance = 10.84831;
    final static double turningRotations = turningDistance / CIRCUMFERENCE;
    final static double turningCounts = ENCODER_CPR * turningRotations * GEAR_RATIO;

    // Foward to mountain calculations
    final static int forwardToMntDistance = 30;
    final static double forwardToMntRotations = forwardToMntDistance / CIRCUMFERENCE;
    final static double forwardToMntCounts = ENCODER_CPR * forwardToMntRotations * GEAR_RATIO;

    enum State {driveForward, turning, driveForwardToMtn, done};
    State state;


    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        switch(state) {
            case driveForward:
                leftMotor.setTargetPosition((int) forwardCounts);
                rightMotor.setTargetPosition((int) forwardCounts);

                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                leftMotor.setPower(1.0);
                rightMotor.setPower(1.0);

                state = State.turning;

            case turning:
                leftMotor.setTargetPosition((int) turningCounts);
                rightMotor.setTargetPosition((int) turningCounts);

                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode((DcMotorController.RunMode.RUN_TO_POSITION));

                leftMotor.setPower(1.0);
                rightMotor.setPower(-1.0);

                state = State.driveForwardToMtn;

            case driveForwardToMtn:
                leftMotor.setTargetPosition((int) forwardToMntCounts);
                rightMotor.setTargetPosition((int) forwardToMntCounts);

                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode((DcMotorController.RunMode.RUN_TO_POSITION));

                leftMotor.setPower(1.0);
                rightMotor.setPower(1.0);

            case done:
                leftMotor.setPower(0.0);
                rightMotor.setPower(0.0);

        }
    }
}
