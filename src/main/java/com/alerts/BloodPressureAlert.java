package com.alerts;

public class BloodPressureAlert implements Alert {

    private String patientId;
    private String condition;
    private long timestamp;

    public BloodPressureAlert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object object){
        if(object.getClass() == this.getClass()){
            Alert alert = (BloodPressureAlert) object;
            if(alert.getPatientId().equals(this.patientId) && alert.getCondition().equals(this.condition) 
            && alert.getTimestamp() == this.timestamp){
                return true;
            }
        }
        return false;
    }

}
