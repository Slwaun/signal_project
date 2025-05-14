package com.alerts;

public class AlertFactory {

    public AlertFactory(){

    }

    public Alert createAlert(String patientId, String conditon, long timeStamp){
        return new Alert(patientId, conditon, timeStamp);
    }

}
