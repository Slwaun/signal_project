package com.alerts;

public class BloodOxygenAlertFactory extends AlertFactory {

    public BloodOxygenAlertFactory(){
        
    }

    @Override
    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new Alert(patientId, conditon, timeStamp);
    }

}
