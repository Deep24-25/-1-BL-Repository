package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;

public class WristFSM {
    private enum WristStates {
        AT_POS,
        GOING_TO_POS,
    }

    private int targetAngle;
    private WristStates wristStates;
    private AxonServoWrapper axonServoWrapper;

    public WristFSM (HWMap hwmap){
        targetAngle = 0;
        wristStates = WristStates.GOING_TO_POS;
        axonServoWrapper = new AxonServoWrapper (hwmap.getWristFlexServo(), hwmap.getWristFlexEncoder(), false, false, 0, 1);
    }

    public void updateState(){
        axonServoWrapper.readPos();
        axonServoWrapper.set(targetAngle);
        if (targetAngle == axonServoWrapper.getLastReadPos()){
            wristStates = WristStates.AT_POS;
        }
        else {
            wristStates = WristStates.GOING_TO_POS;
        }
    }

    public void setTargetAngle(int targetAngle) {
        this.targetAngle = targetAngle;
    }
}
