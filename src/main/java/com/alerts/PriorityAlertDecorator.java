package com.alerts;

import java.util.ArrayList;

import com.data_management.PatientRecord;

public class PriorityAlertDecorator extends AlertDecorator {

    public PriorityAlertDecorator(Alert alert) {
        super(alert);
    }

    public static ArrayList<Alert> priorityAlert(ArrayList<PatientRecord> recordList){
        ArrayList<Alert> priorityList = new ArrayList<>();
        ArrayList<Integer> patientIdList = new ArrayList<>();

        for(int i = 0; i < recordList.size(); i++){
            PatientRecord record = recordList.get(i);
            patientIdList.add(record.getPatientId());
            if(record.getRecordType().equals("SystolicPressure") && (record.getMeasurementValue() < 90.0 || record.getMeasurementValue() > 180.0)){
                priorityList.add(new BloodPressureAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
            } else if(record.getRecordType().equals("DiastolicPressure") && (record.getMeasurementValue() < 60.0 || record.getMeasurementValue() > 120.0)){
                priorityList.add(new BloodPressureAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
            } else if(record.getRecordType().equals("Saturation") && record.getMeasurementValue() < 92.0){
                priorityList.add(new BloodOxygenAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
            } else {
                patientIdList.remove(patientIdList.indexOf(record.getPatientId()));
            }
        }

        for(int i = 0; i < recordList.size(); i++){
            PatientRecord record = recordList.get(i);
            if(record.getRecordType().equals("Alert") && record.getMeasurementValue() == 1.0){
                priorityList.add(new NurseAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
                patientIdList.add(record.getPatientId());
            }
        }

        for(int i = 0; i < recordList.size(); i++){
            PatientRecord record = recordList.get(i);
            if(!patientIdList.contains(record.getPatientId())){
                if(record.getRecordType().equals("SystolicPressure")){
                    priorityList.add(new BloodPressureAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
                } else if(record.getRecordType().equals("DiastolicPressure")){
                    priorityList.add(new BloodPressureAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
                } else if(record.getRecordType().equals("Saturation") && record.getMeasurementValue() < 95.0){
                    priorityList.add(new BloodOxygenAlert(""+record.getPatientId(), record.getRecordType(), record.getTimestamp()));
                }
                patientIdList.add(record.getPatientId());
            }
        }
        return priorityList;
    }

}
