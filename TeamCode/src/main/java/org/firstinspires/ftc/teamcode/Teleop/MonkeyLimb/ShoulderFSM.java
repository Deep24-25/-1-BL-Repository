package org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb;

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

    public ShoulderFSM (HWMap hwMap){
        targetAngle = 0;
        shoulderStates = ShoulderStates.GOING_TO_POS;
        shoulderWrapper = new ShoulderWrapper(hwMap, true);
    }

    public void updateState(){
        shoulderWrapper.readAngle();
        shoulderWrapper.set(targetAngle);
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

}
