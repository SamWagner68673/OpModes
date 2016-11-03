/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="MetalMario1103", group="Testing")

public class MetalMario1103 extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor mtrRF = hardwareMap.dcMotor.get("mtrRF");
        DcMotor mtrRM = hardwareMap.dcMotor.get("mtrRM");
        DcMotor mtrRB = hardwareMap.dcMotor.get("mtrRB");
        DcMotor mtrLF = hardwareMap.dcMotor.get("mtrLF");
        DcMotor mtrLM = hardwareMap.dcMotor.get("mtrLM");
        DcMotor mtrLB = hardwareMap.dcMotor.get("mtrLB");

        mtrRF.setDirection(DcMotor.Direction.REVERSE);
        mtrRM.setDirection(DcMotor.Direction.REVERSE);
        mtrRB.setDirection(DcMotor.Direction.REVERSE);
        mtrLF.setDirection(DcMotor.Direction.FORWARD);
        mtrLM.setDirection(DcMotor.Direction.FORWARD);
        mtrLB.setDirection(DcMotor.Direction.FORWARD);

        double speed;
        double x;
        double rightMultiplier;
        double leftMultiplier;


        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            speed               = -100.0 * gamepad1.left_stick_y;
            x                   =  gamepad1.right_stick_x;
            rightMultiplier     = (x > 0.05) ? (1 - 2 * x) : 1.0;
            leftMultiplier      = (x < -0.05) ? (1 + 2 * x) : 1.0;

            telemetry.addData("time",System.currentTimeMillis() );
            telemetry.addData("speed",speed);
            telemetry.addData("x", x);
            telemetry.update();


            mtrRF.setPower(speed * rightMultiplier);
            mtrRM.setPower(speed * rightMultiplier);
            mtrRB.setPower(speed * rightMultiplier);
            mtrLF.setPower(speed * leftMultiplier);
            mtrLM.setPower(speed * leftMultiplier);
            mtrLB.setPower(speed * leftMultiplier);

        }
    }
}
