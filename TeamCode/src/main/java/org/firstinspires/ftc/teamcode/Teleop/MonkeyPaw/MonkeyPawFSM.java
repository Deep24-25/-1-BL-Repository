package org.firstinspires.ftc.teamcode.Teleop.MonkeyPaw;

import org.firstinspires.ftc.teamcode.Core.HWMap;
import org.firstinspires.ftc.teamcode.Core.Logger;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonCRServoWrapper;
import org.firstinspires.ftc.teamcode.Teleop.Wrappers.AxonServoWrapper;

public class MonkeyPawFSM {
    private enum MonkeyLimbStates {

        //sample

        PREPARING_TO_INTAKE_SAMPLE,
        READY_TO_INTAKE_SAMPLE,
        INTAKING_SAMPLE,
        INTAKED_SAMPLE,
        PREPARING_TO_DEPOSIT_SAMPLE,
        READY_TO_DEPOSIT_SAMPLE,
        DEPOSITING_SAMPLE,
        DEPOSITED_SAMPLE,
        HOVERING_SAMPLE,

        //specimen

        PREPARING_TO_INTAKE_SPECIMEN,
        READY_TO_INTAKE_SPECIMEM,
        INTAKED_SPECIMEN,
        DEPOSITING_TO_HP,
        DEPOSITED_TO_HP,
        PREPARING_TO_DEPOSIT,
        READY_TO_DEPOSIT

    }



    }
