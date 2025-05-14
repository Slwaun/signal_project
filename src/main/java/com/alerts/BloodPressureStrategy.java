package com.alerts;

public class BloodPressureStrategy implements AlertStrategy {

    @Override
    public boolean checkAlert(String condition) {
        if(condition.equals("DiastolicPressure") || condition.equals("SystolicPressure")){
            return true;
        }
        return false;
    }

}
