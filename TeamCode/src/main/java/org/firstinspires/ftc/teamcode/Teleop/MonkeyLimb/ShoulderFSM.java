package org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb;

import static org.firstinspires.ftc.teamcode.Teleop.Constants.*;

import com.arcrobotics.ftclib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Constants;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.ShoulderWrapper;

public class ShoulderFSM {
    private enum ShoulderStates{
        GOING_TO_POS,
        PIVOT_POS,
        DOWN_POS
    }

    private int targetAngle;
    private ShoulderStates shoulderStates;
    private ShoulderWrapper shoulderWrapper;
    private PIDFController pidfController;

    public ShoulderFSM (HWMap hwMap){
        targetAngle = 0;
        shoulderStates = ShoulderStates.GOING_TO_POS;
        pidfController = new PIDFController(shoulderP,shoulderI,shoulderD,shoulderF);
        shoulderWrapper = new ShoulderWrapper(hwMap, true);
    }

    public void updateState(){
        shoulderWrapper.readAngle();
        updatePID();
        if (shoulderWrapper.getLastReadAngle() == targetAngle){
            if (targetAngle == Constants.pivot){
                shoulderStates = ShoulderStates.PIVOT_POS;
            }
            if (targetAngle == Constants.intake){
                shoulderStates = ShoulderStates.DOWN_POS;
            }
        }
        else {
            shoulderStates = ShoulderStates.GOING_TO_POS;
        }

    }

    public void setTargetAngle (int targetAngle){
        this.targetAngle = targetAngle;
    }

    public void updatePID() { // This method is used to update position every loop.
            if (targetAngle >= max)
                targetAngle = max;
            double measuredAngle = shoulderWrapper.getLastReadAngle();
            //This is the error between measured and target position.
            double delta = angleDelta(measuredAngle, targetAngle);
            double sign = angleDeltaSign(measuredAngle, targetAngle);
            // The error * sign (which is direction)
            double error = delta * sign;
            // We use zero because we already calculate for error
            double power = pidfController.calculate(0, error);
            shoulderWrapper.set(power);
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
