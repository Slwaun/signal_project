package com.alerts;

public class BloodPressureAlertFactory extends AlertFactory {

    public BloodPressureAlertFactory(){

    }

    @Override
    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new Alert(patientId, conditon, timeStamp);
    }

}
