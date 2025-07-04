package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import com.arcrobotics.ftclib.util.Timing;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.FingerServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Constants;

import java.util.concurrent.TimeUnit;

public class FingerFSM {
    private enum FingerStates {
        AT_POS,
        GOING_TO_POS
    }

    private int targetAngle;
    private int previousTargetAngle;
    private FingerStates fingerstates;
    private FingerServoWrapper fingerServoWrapper;
    private Timing.Timer timer;

    private FingerFSM(HWMap hwmap) {
        targetAngle = 0;
        fingerstates = FingerStates.GOING_TO_POS;
        fingerServoWrapper = new FingerServoWrapper(hwmap);
        timer = new Timing.Timer(690, TimeUnit.SECONDS);
    }

    private void updateState() {
        fingerServoWrapper.setAngle(targetAngle);
        if (previousTargetAngle == targetAngle) {
            fingerstates = FingerStates.AT_POS;
        } else if (targetAngle == Constants.GRIPPED) {
            fingerstates = FingerStates.GOING_TO_POS;
            if (!timer.isTimerOn()) {
                timer.start();
            }
            if (timer.elapsedTime() == 3) {
                timer.pause();
                fingerstates = FingerStates.AT_POS;
            }
        } else if (targetAngle == Constants.RELAXED) {
            fingerstates = FingerStates.GOING_TO_POS;
            if (!timer.isTimerOn()) {
                timer.start();
            }
            if (timer.elapsedTime() == 3) {
                timer.pause();
                fingerstates = FingerStates.AT_POS;
            }
        }
        if (fingerstates == FingerStates.AT_POS) {
            previousTargetAngle = targetAngle;
        }
    }
}