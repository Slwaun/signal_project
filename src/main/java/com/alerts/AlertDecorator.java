package com.alerts;

public abstract class AlertDecorator implements Alert {

    private Alert alert;

    public AlertDecorator(Alert alert){
        this.alert = alert;
    }

    public String getPatientId(){
        return alert.getPatientId();
    }

    public String getCondition(){
        return alert.getCondition();
    }

    public long getTimestamp(){
        return alert.getTimestamp();
    }

}
