package org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb;

import static org.firstinspires.ftc.teamcode.Teleop.Constants.*;

import com.arcrobotics.ftclib.controller.PIDFController;


import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.ArmMotorsWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;

public class ArmFSM {
    private enum ArmStates{
        AT_POS,
        GOING_TO_POS,
        FEEDING,
        DEPOSITING_SPECIMEN
    }

    private int targetAngle;
    private int chamberDeposit;
    private ArmStates armStates;
    private ArmMotorsWrapper armMotorsWrapper;
    private PIDFController pidfController;

    public ArmFSM(HWMap hwMap){
        targetAngle = 0;
        armStates = ArmStates.GOING_TO_POS;
        pidfController = new PIDFController(armP, armI, armD, armF);
        armMotorsWrapper = new ArmMotorsWrapper(hwMap, true);
    }

    public void updateState(){
        armMotorsWrapper.readPositionInCM();
        if (!RobotFSM.shouldPID){
            armStates = ArmStates.FEEDING;
            setFeed();
        }
        else {
            updatePID();
            if (targetAngle == chamberDeposit) {
                armStates = ArmStates.DEPOSITING_SPECIMEN;
                if (armMotorsWrapper.getAM1Current() == 0 && armMotorsWrapper.getAM2Current() == 0 && armMotorsWrapper.getAM3Current() == 0) {
                    armStates = ArmStates.AT_POS;
                }
            } else if (armMotorsWrapper.getLastReadPositionInCM() != targetAngle) {
                armStates = ArmStates.GOING_TO_POS;
            } else if (armMotorsWrapper.getLastReadPositionInCM() == targetAngle) {
                armStates = ArmStates.AT_POS;
            }
        }
    }

    public void setTargetAngle (int targetAngle){
        this.targetAngle = targetAngle;
    }

    /*or just do the feed method in INTO THE DEEP?
    public void setFeed(){
        RobotFSM.setArmPIDF();
    }

    public double getCurrentFeedrate() {
        return currentFeedrate;
    }

    public static double getMaxFeedrate() {
        return MAX_FEEDRATE;
    }
     */

    public void updatePID() { // This method is used to update position every loop.
        if (targetAngle >= max)
            targetAngle = max;
        double measuredAngle = armMotorsWrapper.getLastReadPositionInCM();
        //This is the error between measured and target position.
        double delta = angleDelta(measuredAngle, targetAngle);
        double sign = angleDeltaSign(measuredAngle, targetAngle);
        // The error * sign (which is direction)
        double error = delta * sign;
        // We use zero because we already calculate for error
        double power = pidfController.calculate(0, error);
        armMotorsWrapper.set(power);
    }
    protected double angleDelta(double measuredAngle, double targetAngle) {
        return Math.min(normalizeDegrees(measuredAngle - targetAngle), 360 - normalizeDegrees(measuredAngle - targetAngle));
    }
    protected double angleDeltaSign(double measuredAngle, double targetAngle) {
        return -(Math.signum(normalizeDegrees(targetAngle - measuredAngle) - (360 - normalizeDegrees(targetAngle - measuredAngle))));
    }
    protected static double normalizeDegrees(double angle) {
        return (angle + 360) % 360;
    }
}
