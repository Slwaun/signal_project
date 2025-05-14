package com.alerts;

public class HeartRateStrategy implements AlertStrategy {

    @Override
    public boolean checkAlert(String condition){
        if(condition.equals("")){
            return true;
        }
        return false;
    }

}
