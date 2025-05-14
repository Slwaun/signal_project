package com.alerts;

public class ECGAlertFactory extends AlertFactory {

    public ECGAlertFactory(){
        
    }

    @Override
    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new Alert(patientId, conditon, timeStamp);
    }

}
