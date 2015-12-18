package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LegacyModule;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by FTC10267 on 10/28/2015.
 */
public class Hardware extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor beltMotor;
    DcMotor winchMotor;
    Servo backArm;
    Servo frontArm;

    float oneLeftY = 0;
    float oneRightY = 0;
    float twoLeftY = 0;
    float twoRightY = 0;

    @Override
    public void init() {
        // KEEP THIS METHOD HERE
    }

    @Override
    public void loop() {
        // KEEP THIS METHOD HERE
    }

    // Accesses the left drive power
    public double leftDrive_Power(){
        double drivePower = 0.0;

        if (leftMotor != null) {
            drivePower = leftMotor.getPower();
        }

        return drivePower;
    }

    // Accesses the right drive power
    public double rightDrive_Power() {
        double drivePower = 0.0;

        if (rightMotor != null){
            drivePower = rightMotor.getPower();
        }
        return drivePower;
    }

    //--------------------------------------------------------------------------
    /**
     * THE FOLLOWING CODE IS RESERVED TO WARNING VARIABLES AND METHODS
     */

}
