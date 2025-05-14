package com.alerts;

public class BloodPressureAlertFactory extends AlertFactory {

    @Override
    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new BloodPressureAlert(patientId, conditon, timeStamp);
    }

}
