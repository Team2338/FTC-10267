package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC10267 on 11/19/2015.
 */
public class AutoStateMachine extends OpMode {
    // Declare and initialize constants
    DcMotor leftMotor;
    DcMotor rightMotor;
    ElapsedTime time;

    final static int ENCODER_CPR = 1440;     //Encoder Counts per Revolution
    final static double GEAR_RATIO = 3;      //Gear Ratio
    final static int WHEEL_DIAMETER = 4;     //Diameter of the wheel in inches
    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

    // Driving straight calculations
    int forwardDistance = 24;          //Distance in inches to drive forward
    double forwardRotations = forwardDistance / CIRCUMFERENCE;
    double forwardCounts = ENCODER_CPR * forwardRotations * GEAR_RATIO;

    // Turning calculations
    final static double turnTime = 1.0;

    // Declaring states of StateMachine
    enum State {drivingStraight, turning, done};
    State state;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        time = new ElapsedTime();
        state = State.drivingStraight;
    }

    @Override
    public void loop() {
        double currentTime = time.time();
        switch(state) {
            case drivingStraight:
                leftMotor.setTargetPosition((int) forwardCounts);
                rightMotor.setTargetPosition((int) forwardCounts);

                leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                leftMotor.setPower(1.0);
                rightMotor.setPower(1.0);

                state = State.turning;
                time.reset();
            case turning:
                leftMotor.setPower(1.0);
                rightMotor.setPower(-1.0);
                if (currentTime > turnTime) {
                    state = State.done;
                    time.reset();
                }
            case done:
                leftMotor.setPower(0);
                rightMotor.setPower(0);
        }
    }
}
