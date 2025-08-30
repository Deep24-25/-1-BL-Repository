package org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb.ArmFSM;
import org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb.ShoulderFSM;

public class MonkeyLimbFSM {

    private enum Mode {
        SAMPLE_MODE,
        SPECIMEN_MODE,
    }

    private enum MonkeyLimbStates{
        //Sample
        MOVING_TO_INTAKE_SAMPLE,
        INTAKED_SAMPLE,
        INTAKING_SAMPLE,
        READY_TO_INTAKE_SAMPLE,
        PREPARING_TO_INTAKE_SAMPLE,
        READY_TO_DEPOSIT_SAMPLE,
        PREPARING_TO_DEPOSIT_SAMPLE,
        //Specimen
        PREPARING_TO_INTAKE_SPECIMEN,
        READY_TO_INTAKE_SPECIMEN,
        READY_TO_DEPOSIT_SPECIMEN,
        PREPARING_TO_DEPOSIT_SPECIMEN,
        DEPOSITING_SPECIMEN();

        double armTargetAngle;
        double shoulderTargetAngle;
    }

    private Mode mode;
    private MonkeyLimbStates monkeyLimbStates;
    private ArmFSM armFSM;
    private ShoulderFSM shoulderFSM;

    public MonkeyLimbFSM(ArmFSM armFSM, ShoulderFSM shoulderFSM, HWMap hwMap){
        monkeyLimbStates = MonkeyLimbStates.MOVING_TO_INTAKE_SAMPLE;
        mode = Mode.SAMPLE_MODE;
        this.armFSM = armFSM;
        this.shoulderFSM = shoulderFSM;
    }

    public void updateStates(){
        switch(monkeyLimbStates){
            case PREPARING_TO_INTAKE_SPECIMEN:
                if (armFSM.AT_POS() && shoulderFSM.DOWN()){
                    monkeyLimbStates = MonkeyLimbStates.READY_TO_INTAKE_SPECIMEN;
                }
            break;

            case (PREPARING_TO_DEPOSIT_SPECIMEN):
                if (armFSM.AT_POS() && shoulderFSM.PIVOTED()){
                    monkeyLimbStates = MonkeyLimbStates.READY_TO_DEPOSIT_SPECIMEN;
                }
            break;

            case DEPOSITING:
                if (armFSM.AT_POS() && shoulderFSM.PIVOTED()){
                    monkeyLimbStates = MonkeyLimbStates.DEPOSITING_SPECIMEN;
                }
        }
    }

}
