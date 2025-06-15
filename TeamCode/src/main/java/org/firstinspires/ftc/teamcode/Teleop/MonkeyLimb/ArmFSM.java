package org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.ArmMotorsWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;

public class ArmFSM {
    private enum ArmStates{
        AT_POS,
        GOING_TO_POS,
        FEEDING,
        DEPOSITING_SPECIMEN
    }

    private int targetAngle;
    private int chamberDeposit;
    private int currentThreshold;
    private ArmStates armStates;
    private ArmMotorsWrapper armMotorsWrapper;

    public ArmFSM(HWMap hwMap){
        targetAngle = 0;
        armStates = ArmStates.GOING_TO_POS;
        armMotorsWrapper = new ArmMotorsWrapper(hwMap, true);
    }

    public void updateState(){
        if (!RobotFSM.shouldPID){
            armStates = ArmStates.FEEDING;
            setFeed();
        }
        armMotorsWrapper.readPositionInCM();
        armMotorsWrapper.set(targetAngle);
        if (targetAngle == chamberDeposit){
            armStates = ArmStates.DEPOSITING_SPECIMEN;
            if (armMotorsWrapper.getAM1Current() == 0 && armMotorsWrapper.getAM2Current() == 0 && armMotorsWrapper.getAM3Current() == 0){
                armStates = ArmStates.AT_POS;
            }
        }
        else if (armMotorsWrapper.getLastReadPositionInCM() != targetAngle){
            armStates = ArmStates.GOING_TO_POS;
        }
        else if (armMotorsWrapper.getLastReadPositionInCM() == targetAngle){
            armStates = ArmStates.AT_POS;
        }
    }

    public void setFeed(){
        RobotFSM.setArmPIDF();
    }

    public double getCurrentFeedrate() {
        return currentFeedrate;
    }

    public static double getMaxFeedrate() {
        return MAX_FEEDRATE;
    }
}
