package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.util.Timing;


import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.FingerServoWrapper;

import java.util.concurrent.TimeUnit;

@Config
public class FingerFSM {
    private enum FingerStates {
        GOING_TO_POS,
        AT_POS
    }

    private double targetAngle;

    public static double GRIPPED = 0.671;
    public static double RELEASED = 0.951;

    private final FingerServoWrapper fingerServoWrapper;

    private FingerStates state;
    private final Logger logger;
    private final Timing.Timer timer;

    public static long OFFSET = 25;


    public FingerFSM(HWMap hwmap, Logger logger) {
        fingerServoWrapper = new FingerServoWrapper(hwmap);
        this.logger = logger;
        timer = new Timing.Timer(OFFSET, TimeUnit.MILLISECONDS);
        state = FingerStates.GOING_TO_POS;
    }

    public void updateState() {
        fingerServoWrapper.readAngle();
        fingerServoWrapper.setAngle(targetAngle);

    }
}
