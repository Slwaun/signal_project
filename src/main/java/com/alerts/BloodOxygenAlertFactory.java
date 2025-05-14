package com.alerts;

public class BloodOxygenAlertFactory extends AlertFactory {

    @Override
    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new BloodOxygenAlert(patientId, conditon, timeStamp);
    }

}
