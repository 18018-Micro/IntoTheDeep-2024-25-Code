package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testOpMode extends LinearOpMode {

    DcMotor testMotor = hardwareMap.dcMotor.get("testMotor");
    @Override
    public void runOpMode() {

        if (gamepad1.a) {
            testMotor.setPower(0.8);
        } else if (gamepad1.b) {
            testMotor.setPower(-0.8);
        } else {
            testMotor.setPower(0);
        }

    }
}
