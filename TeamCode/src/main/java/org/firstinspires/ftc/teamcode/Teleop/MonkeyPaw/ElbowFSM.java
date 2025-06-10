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

    }

    public void updateState(){

    }
}
