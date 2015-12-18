package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC10267 on 10/21/2015.
 */
public class TeleOp extends Telemetry {

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive"); // Connects the left drive wheel motor
        rightMotor = hardwareMap.dcMotor.get("right_drive"); // Connects the right drive wheel motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        backArm = hardwareMap.servo.get ("back_arm"); // Connects the back arm servo motor
        frontArm = hardwareMap.servo.get ("front_arm"); // Connects the front arm gate servo motor

        winchMotor = hardwareMap.dcMotor.get("winch_motor"); // Connects the arm angle motor
        beltMotor = hardwareMap.dcMotor.get("belt_motor"); // Connects the arm belt motor

        frontArm.setPosition(1.0);
        backArm.setPosition(0.5);
    }


    @Override
    public void loop() {
    oneLeftY = -gamepad1.left_stick_y;
    oneRightY = -gamepad1.right_stick_y;
    twoLeftY = -gamepad2.left_stick_y;
    twoRightY = gamepad2.right_stick_y;

    leftMotor.setPower(oneLeftY);
    rightMotor.setPower(oneRightY);
    winchMotor.setPower(twoLeftY);
    beltMotor.setPower(twoRightY);

    if ((gamepad1.right_bumper) && (frontArm.getPosition() == 0.5) || frontArm.getPosition() == 0.49 || frontArm.getPosition() == 0.51) {
        frontArm.setPosition(1.0);
    }

    if ((gamepad1.right_bumper) && (frontArm.getPosition() == 1.0) || frontArm.getPosition() == 0.99 || frontArm.getPosition() == 1.01) {
        frontArm.setPosition(0.5);
    }

    if ((gamepad1.left_bumper) && (backArm.getPosition() == 0.5) || backArm.getPosition() == 0.49 || backArm.getPosition() == 0.51) {
        backArm.setPosition(1.0);
    }

    if ((gamepad1.left_bumper) && (backArm.getPosition() == 1.0) || backArm.getPosition() == 0.99 || backArm.getPosition() == 1.01) {
        backArm.setPosition(0.5);
    }

    telemetry.addData("00:", "Back Arm Position: " + backArm.getPosition());
    telemetry.addData("01:", "Front Arm Position: " + frontArm.getPosition ());
    telemetry.addData("02:", "Winch Position: " + winchMotor.getCurrentPosition());
    telemetry.addData("03:", "Belt Position: " + beltMotor.getCurrentPosition());
    update_telemetry();
    update_gamepad_telemetry();
    }
}
