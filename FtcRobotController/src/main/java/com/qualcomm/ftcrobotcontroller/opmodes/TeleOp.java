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
    }

    @Override
    public void loop() {
    oneLeftY = -gamepad1.left_stick_y;
    oneRightY = -gamepad1.right_stick_y;
    twoRightY = gamepad2.right_stick_y;

    leftMotor.setPower(oneLeftY);
    rightMotor.setPower(oneRightY);
    beltMotor.setPower(twoRightY);

    if (gamepad2.dpad_left) {
        backArm.setPosition(DOWN_POSITION);
    }
    if (gamepad2.dpad_right) {
        backArm.setPosition(UP_POSITION);
    }

    if (gamepad1.dpad_left) {
        frontArm.setPosition(DOWN_POSITION);
    }
    if (gamepad1.dpad_right) {
        frontArm.setPosition(UP_POSITION);
    }

    if (gamepad2.y) {
        winchMotor.setPower(0.2);
    }
    if (gamepad2.a) {
        winchMotor.setPower(-0.2);
    }

    telemetry.addData("00:", "Back Arm Position: " + backArm.getPosition());
    telemetry.addData("01:", "Front Arm Position: " + frontArm.getPosition ());
    telemetry.addData("02:", "Winch Position: " + winchMotor.getCurrentPosition());
    telemetry.addData("03:", "Belt Position: " + beltMotor.getCurrentPosition());
    update_telemetry();
    update_gamepad_telemetry();
    }
}
