package com.alerts;

public class OxygenSaturationStrategy implements AlertStrategy{

    @Override
    public boolean checkAlert(String condition) {
        if(condition.equals("Saturation")){
            return true;
        }
        return false;
    }

}
