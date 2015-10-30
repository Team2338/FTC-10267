package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by FTC10267 on 10/28/2015.
 */
public class Hardware extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor beltMotor;
    DcMotor winchMotor;
    Servo servo;

    double UP_POSITION = 1.0;
    double DOWN_POSITION = 0.0;
    double SERVO_START = 0.0;
    float leftY = 0;
    float rightY = 0;

    @Override
    public void init() {

        warningGenerated = false;
        warningMessage = "Can't map: ";

        // Connects the left drive wheel motor
        // If the left drive motor cannot be found, the app gives an error
        try
        {
            leftMotor = hardwareMap.dcMotor.get("left_drive");
        }
        catch (Exception exception) {
            mutateWarningMessage("left_drive");

            DbgLog.msg(exception.getLocalizedMessage());

            leftMotor = null;
        }

        // Connects the right drive wheel motor
        // If the right drive motor cannot be found, the app gives an error
        try
        {
            rightMotor = hardwareMap.dcMotor.get("right_drive");
            rightMotor.setDirection(DcMotor.Direction.REVERSE);
        }
        catch (Exception exception)
        {
            mutateWarningMessage("right_drive");
            DbgLog.msg (exception.getLocalizedMessage ());

            rightMotor = null;
        }

        // Connects the unnamed subsystem servo motor
        // If the servo motor cannot be found, the app gives an error
        try
        {
            servo = hardwareMap.servo.get ("servo");
            servo.setPosition(SERVO_START);
        }
        catch (Exception exception)
        {
            mutateWarningMessage("servo");
            DbgLog.msg(exception.getLocalizedMessage());

            servo = null;
        }

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
    private boolean warningGenerated = false;
    private String warningMessage;

    // Accesses whether a warning has been generated
    public boolean accessWarningGenerated ()
    {
        return warningGenerated;
    }

    // Accesses the warning message
    public String accessWarningMessage ()
    {
        return warningMessage;
    }

    /**
     * Mutates the warning message by adding the specified message to the current
     * message; sets the warning indicator to true
     *
     * A comma will be added before the specified message if the message isn't empty
     */
    public void mutateWarningMessage (String exceptionMessage)

    {
        if (warningGenerated)
        {
            warningMessage += ", ";
        }
        warningGenerated = true;
        warningMessage += exceptionMessage;
    }




}
