package org.firstinspires.ftc.teamcode.Teleop.MonkeyLimb;

import org.firstinspires.ftc.teamcode.Core.HWMap;

public class MonkeyLimbFSM {

    private enum Mode {
        SAMPLE_MODE,
        SPECIMEN_MODE,
    }

    private Mode mode;
    private MonkeyLimbStates monkeyLimbStates;
    private static ArmFSM armFSM;
    private final ShoulderFSM shoulderFSM;

    double armTargetAngle;
    double shoulderTargetAngle;

    private enum MonkeyLimbStates{
        //Sample
        MOVING_TO_INTAKE_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        INTAKED_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        INTAKING_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        READY_TO_INTAKE_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        PREPARING_TO_INTAKE_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        READY_TO_DEPOSIT_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        PREPARING_TO_DEPOSIT_SAMPLE() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        //Specimen
        PREPARING_TO_INTAKE_SPECIMEN() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        READY_TO_INTAKE_SPECIMEN() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        READY_TO_DEPOSIT_SPECIMEN() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        PREPARING_TO_DEPOSIT_SPECIMEN() {
            @Override
            public double setTargetAngle(){
                return 0;
            }
        },
        DEPOSITING_SPECIMEN() {
            public void setTargetAngle(){
                return targetAngle;
            }
        };
        public double targetAngle;
        public void setTargetPosition(double armPosition, double shoulderPosition){
            armFSM.setTargetAngle(armPosition);
            if (armFSM.;
        }
    }


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
