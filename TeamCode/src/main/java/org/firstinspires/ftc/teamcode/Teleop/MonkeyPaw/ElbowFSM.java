package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;

public class ElbowFSM {
    private enum ElbowStates{
        AT_POS,
        GOING_TO_POS
    }

    private int targetAngle;
    private ElbowStates elbowStates;
    private AxonServoWrapper axonServoWrapper;

    public ElbowFSM (HWMap hwMap){
    targetAngle = 0;
    elbowStates = ElbowStates.GOING_TO_POS;
    axonServoWrapper = new AxonServoWrapper(hwMap.getElbowServo(), hwMap.getElbowEncoder(), false, false, 0, 1);
    }

    public void updateState(){
    axonServoWrapper.readPos();
    axonServoWrapper.set(targetAngle);
    if (targetAngle == axonServoWrapper.getLastReadPos()){
        elbowStates = ElbowStates.AT_POS;
    }
    else{
        elbowStates = ElbowStates.GOING_TO_POS;
        }
    }

    public void setTargetAngle(int targetAngle) {
        this.targetAngle = targetAngle;
    }
}
