package org.firstinspires.ftc.teamcode.Auton.Odometry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "odometryTest", group = "")
public class odometryTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor cm1 = hardwareMap.dcMotor.get("chm1");
        DcMotor cm2 = hardwareMap.dcMotor.get("chm2");
        DcMotor cm3 = hardwareMap.dcMotor.get("chm3");
        DcMotor cm4 = hardwareMap.dcMotor.get("chm4");

        waitForStart();
        if (opModeIsActive()) {


            cm1.setPower(0);
            cm2.setPower(0);
            cm3.setPower(0);
            cm4.setPower(0);


        }


    }

}
