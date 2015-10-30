package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by FTC10267 on 10/21/2015.
 */
public class TeleOp extends Telemetry {

    @Override
    public void init() {
        /* beltMotor = hardwareMap.dcMotor.get("belt_motor");
        winchMotor = hardwareMap.dcMotor.get("winch_motor"); */

    }

    @Override
    public void loop() {
    leftY = -gamepad1.left_stick_y;
    rightY = -gamepad1.right_stick_x;
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

    if (gamepad2.dpad_left){
        servo.setPosition(UP_POSITION);
    }
    if (gamepad2.dpad_right){
        servo.setPosition(DOWN_POSITION);
    }

    /* if (gamepad2.a) {
        winchMotor.setPower(0.2);
    } */

    update_telemetry();
    update_gamepad_telemetry ();
    }
}
