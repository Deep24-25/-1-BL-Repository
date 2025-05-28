package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import com.acmerobotics.dashboard.

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;

public class DeviatorFSM {
    private enum DeviatorStates{
        GOING_TO_POS,
        AT_POS
    }

    private double targetAngle;
    private static double TOLERANCE = 360;
    public static double P = 0.005;
    public static double I = 0;
    public static double D = 0;


    public static double RIGHT_DEVIATED_POS = 20.5;
    public static double LEFT_DEVIATED_POS = 123;
    public static double RELAXED_POS = 151;
    public static double VERTICAL_POS = 253;
    public static double CHAMBER_DEPOSIT_POS = 273;


    private final AxonServoWrapper deviatorServoWrapper;

    private DeviatorStates state;
    private final Logger logger;

    private int currentIndex = 0;

    private final double[] deviations = {RELAXED_POS, 145.5, LEFT_DEVIATED_POS, 100.5, VERTICAL_POS, 43, RIGHT_DEVIATED_POS, 20, 0};

    private static final double RATIO = 1.0;
    public DeviatorFSM(HWMap hwMap, Logger logger) {
        deviatorServoWrapper = new AxonServoWrapper(hwMap.getWristDeviServo(), hwMap.getWristDeviEncoder(), false, false, 0, RATIO); // check if you need to reverse axons
        this.logger = logger;
        relax(); // Need this so target angle is set to the relax position setting state would do nothing as that itself does not change target angle
        state = DeviatorStates.;

    }

}
