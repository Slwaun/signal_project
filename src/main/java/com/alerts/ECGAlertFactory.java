package com.alerts;

public class ECGAlertFactory extends AlertFactory {

    @Override
    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new ECGAlert(patientId, conditon, timeStamp);
    }

}
