package com.alerts;

public abstract class AlertFactory {

    public abstract Alert createAlert(String patientId, String conditon, long timeStamp);

}