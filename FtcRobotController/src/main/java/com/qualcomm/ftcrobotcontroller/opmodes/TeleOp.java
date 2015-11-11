package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC10267 on 10/21/2015.
 */
public class TeleOp extends Telemetry {

    @Override
    public void init() {
        /* beltMotor = hardwareMap.dcMotor.get("belt_motor");
        winchMotor = hardwareMap.dcMotor.get("winch_motor"); */
        // Connects the left drive wheel motor
        // If the left drive motor cannot be found, the app gives an error
        leftMotor = hardwareMap.dcMotor.get("left_drive");

        // Connects the right drive wheel motor
        // If the right drive motor cannot be found, the app gives an error
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Connects the unnamed subsystem servo motor
        // If the servo motor cannot be found, the app gives an error
        /* servo = hardwareMap.servo.get ("servo");
        servo.setPosition(SERVO_START); */
    }

    @Override
    public void loop() {
    leftY = -gamepad1.left_stick_y;
    rightY = -gamepad1.right_stick_y;
    /* if (gamepad2.right_bumper) {
        beltMotor.setPower(0.2);
    }
    else if (gamepad2.left_bumper) {
        beltMotor.setPower(-0.2);
    }
    else {
        beltMotor.setPower(0);
    } */
    leftMotor.setPower(leftY);
    rightMotor.setPower(rightY);

    /* if (gamepad2.dpad_left){
        servo.setPosition(UP_POSITION);
    }
    if (gamepad2.dpad_right){
        servo.setPosition(DOWN_POSITION);
    } */

    /* if (gamepad2.a) {
        winchMotor.setPower(0.2);
    } */

    update_telemetry();
    update_gamepad_telemetry ();
    }
}
