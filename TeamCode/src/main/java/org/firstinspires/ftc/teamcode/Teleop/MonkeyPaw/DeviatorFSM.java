package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;

public class DeviatorFSM {
    private enum DeviatorStates {
        GOING_TO_POS,
        AT_POS
    }

    private int targetAngle;
    private DeviatorStates deviatorStates;
    private AxonServoWrapper axonServoWrapper;


    public DeviatorFSM (HWMap hwMap){
        targetAngle = 0;
        deviatorStates = DeviatorStates.GOING_TO_POS;
        axonServoWrapper = new AxonServoWrapper(hwMap.getWristDeviServo(), hwMap.getWristDeviEncoder(), false, false, 0, 1);
    }

    public void updateState () {
        axonServoWrapper.readPos();
        axonServoWrapper.set(targetAngle);
        if (targetAngle == axonServoWrapper.getLastReadPos()){
            deviatorStates = DeviatorStates.AT_POS;
        }
        else {
            deviatorStates = DeviatorStates.GOING_TO_POS;
        }
    }

    public void setTargetAngle(int targetAngle){
        this.targetAngle = targetAngle;
    }
}
