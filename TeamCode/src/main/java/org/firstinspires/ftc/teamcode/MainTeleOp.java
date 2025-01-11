// Team 20148 Micropachycephalosaurus
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "mainTeleOp", group = "")
public class MainTeleOp extends LinearOpMode {

    // This function is executed when this Op Mode is selected from the Driver Station.
    @Override
    public void runOpMode() {

        double backLeft_target;
        double backRight_target;
        double frontLeft_target;
        double frontRight_target;
        double turningPower;
        double powerLimiter = 0.85;
        int intakeTargetPos = 0;

        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        Servo intakeServoL = hardwareMap.servo.get("intakeServoL");
        Servo intakeServoR = hardwareMap.servo.get("intakeServoR");
        DcMotorEx armExtender = (DcMotorEx) hardwareMap.dcMotor.get("armExtender");
        DcMotorEx armAngle = (DcMotorEx) hardwareMap.dcMotor.get("armAngle");


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeServoR.setDirection(Servo.Direction.REVERSE);
        intakeServoL.setDirection(Servo.Direction.FORWARD);

        armExtender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armExtender.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armAngle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armAngle.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();
        if (opModeIsActive()) {

            while (opModeIsActive()) {
                /*
                Control Hub
                    Back left:backLeft, back right:backRight
                    Front left:frontLeft, front right:frontRight
                Expansion Hub:
                    0: armExtender
                    1: armAngle
                 */

                //turning script
                double rotate = 0;
                boolean rotating;
                turningPower = 0.85;

                // If either our the bumpers are pressed, rotate the robot in that direction.
                if (gamepad1.right_bumper) {
                    rotating = true;
                    rotate = -turningPower;
                } else if (gamepad1.left_bumper) {
                    rotating = true;
                    rotate = turningPower;
                } else {
                    rotating = false;
                }

                // straight script
                boolean straight = false;
                double straightPwr;

                if (gamepad1.right_trigger > 0) {
                    straight = true;
                    straightPwr = gamepad1.right_trigger;
                } else if (gamepad1.left_trigger > 0) {
                    straight = true;
                    straightPwr = -gamepad1.left_trigger;
                } else {
                    straightPwr = 0;
                }


                // Math for full rotational movement (anywhere with the joystick)
                if ((Math.abs(gamepad1.left_stick_x) + Math.abs(gamepad1.left_stick_y) > 0.15) || rotating || straight) {
                    if (gamepad1.left_stick_x >= 0 && -gamepad1.left_stick_y >= 0) {
                        // Quadrant I
                        backLeft_target = Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (-straightPwr);
                        backRight_target = -Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (-straightPwr);
                        frontLeft_target = Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (straightPwr);
                        frontRight_target = -Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (straightPwr);
                    } else if (gamepad1.left_stick_x < 0 && -gamepad1.left_stick_y > 0) {
                        // Quadrant II
                        backLeft_target = -Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (-straightPwr);
                        backRight_target = Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (-straightPwr);
                        frontLeft_target = -Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (straightPwr);
                        frontRight_target = Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (straightPwr);
                    } else if (gamepad1.left_stick_x <= 0 && -gamepad1.left_stick_y <= 0) {
                        // Quadrant III
                        backLeft_target = -Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (-straightPwr);
                        backRight_target = Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (-straightPwr);
                        frontLeft_target = -Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (straightPwr);
                        frontRight_target = Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (straightPwr);
                    } else if (gamepad1.left_stick_x > 0 && -gamepad1.left_stick_y < 0) {
                        // Quadrant IV
                        backLeft_target = Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (-straightPwr);
                        backRight_target = -Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (-straightPwr);
                        frontLeft_target = Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (-rotate) + (straightPwr);
                        frontRight_target = -Math.pow(gamepad1.left_stick_x, 2) - Math.pow(gamepad1.left_stick_y, 2) + (rotate) + (straightPwr);
                    } else {
                        backLeft_target = rotate + straightPwr;
                        backRight_target = -rotate + straightPwr;
                        frontLeft_target = -rotate + straightPwr;
                        frontRight_target = rotate + straightPwr;
                    }
                } else {
                    // Reset our motors
                    backLeft_target = 0;
                    backRight_target = 0;
                    frontLeft_target = 0;
                    frontRight_target = 0;
                }

                // Set the power for our motors (and apply power limiters)
                backLeft.setPower(backLeft_target * powerLimiter);
                backRight.setPower(backRight_target * powerLimiter);
                frontLeft.setPower(frontLeft_target * powerLimiter);
                frontRight.setPower(frontRight_target * powerLimiter);


                if (gamepad1.a) {
                    intakeTargetPos += 1;
                    intakeServoL.setPosition(intakeTargetPos);
                    intakeServoR.setPosition(intakeTargetPos);
                } if (gamepad1.b) {
                    intakeTargetPos -= 1;
                    intakeServoL.setPosition(intakeTargetPos);
                    intakeServoR.setPosition(intakeTargetPos);
                }


                // Add telemetry data, so we can observe what is happening on the Driver Station
//                telemetry.addData("backLeft", backLeft_target);
//                telemetry.addData("backRight", backRight_target);
//                telemetry.addData("frontLeft", frontLeft_target);
//                telemetry.addData("frontRight", frontRight_target);
//                telemetry.addData("rotating", rotating);
                telemetry.addData("servoTargetPos", intakeTargetPos);
                telemetry.update();
            }
        }
    }
}