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

        servo = hardwareMap.servo.get ("servo"); // Connects the unnamed subsystem servo motor

        // beltMotor = hardwareMap.dcMotor.get("belt_motor");
        // winchServo = hardwareMap.servo.get("winch_servo");
    }

    @Override
    public void loop() {
    leftY = gamepad1.left_stick_y;
    rightY = gamepad1.right_stick_y;

    leftMotor.setPower(leftY);
    rightMotor.setPower(rightY);

    if (gamepad2.dpad_left) {
        servo.setPosition(DOWN_POSITION);
    }
    if (gamepad2.dpad_right) {
        servo.setPosition(UP_POSITION);
    }

/*    if (gamepad2.right_bumper) {
        beltMotor.setPower(0.2);
    }
    else if (gamepad2.left_bumper) {
        beltMotor.setPower(-0.2);
    }
    else {
        beltMotor.setPower(0);
    }

    if (gamepad2.a) {
        winchServo.setPosition(0.2);
    }
    if (gamepad2.y) {
        winchServo.setPosition(1.0);
    } */

    telemetry.addData("00:", "Servo Position: " + servo.getPosition());
    update_telemetry();
    update_gamepad_telemetry();
    }
}
