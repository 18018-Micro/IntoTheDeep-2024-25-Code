package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import  com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "testOpMode", group = "")
public class testOpMode extends LinearOpMode {


    DcMotorEx armEx = (DcMotorEx) hardwareMap.dcMotor.get("armExtender");
    DcMotorEx armAngle = (DcMotorEx) hardwareMap.dcMotor.get("armAngle");

    @Override
    public void runOpMode() {

        if (gamepad1.a) {
            armAngle.setPower(0.5);
        } else if (gamepad1.b) {
            armAngle.setPower(-0.5);
        } else {
            armAngle.setPower(0);
        }

        if (gamepad1.left_stick_y > 0.1) {
            armEx.setPower(0.5);
        } else if (gamepad1.left_stick_y < -0.1) {
            armEx.setPower(-0.5);
        } else {
            armEx.setPower(0);
        }

    }
}
