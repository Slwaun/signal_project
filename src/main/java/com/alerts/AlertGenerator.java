package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        // Implementation goes here
        //ECG, Saturation, chlosterole, redcell and whitecell....
        long startTime = 0;
        long endTime = 1200000;
        List<PatientRecord> patientRecords = patient.getRecords(startTime, endTime);
        double min = 0.0;
        double max = 0.0;

        for(int i = (int) startTime; i<((int) endTime); i++){
            String patientId = Integer.toString(patientRecords.get(i).getPatientId());
            String record = patientRecords.get(i).getRecordType();
            double measurement = patientRecords.get(i).getMeasurementValue();
            long timeStamp = patientRecords.get(i).getTimestamp();

             if (record.equals("SystolicPressure") || record.equals("DiastolicPressure")) {
                if (record.equals("SystolicPressure")) {
                    if (measurement>180.0 || measurement<90) {
                        triggerAlert(new Alert(patientId,record,timeStamp));
                    }
                }
                if (record.equals("DiastolicPressure")) {
                    if (measurement>120.0 || measurement<60) {
                        triggerAlert(new Alert(patientId,record,timeStamp));
                    }
                }
                if (i == (int) startTime || i == ((int) startTime)-1) {
                    double[] tmp = new double[3];
                    for(int j = 0;j<tmp.length;j++){
                       tmp[j] = Math.abs(patientRecords.get(j).getMeasurementValue() + patientRecords.get(j+1).getMeasurementValue()); 
                    }
                    if(tmp[0]>10 && tmp[1]>10 && tmp[2]>10){
                        triggerAlert(new Alert(patientId,record,timeStamp));
                    }
                }
                else if (i == (int) endTime) {
                    double[] tmp = new double[3];
                    for(int j = ((int) endTime)-1;j<tmp.length;j--){
                       tmp[j] = Math.abs(patientRecords.get(j).getMeasurementValue() + patientRecords.get(j-1).getMeasurementValue()); 
                    }
                    if(tmp[0]>10 && tmp[1]>10 && tmp[2]>10){
                        triggerAlert(new Alert(patientId,record,timeStamp));
                    }
                } else {   
                    double cond1 = Math.abs(patientRecords.get(i).getMeasurementValue() + patientRecords.get(i-1).getMeasurementValue());
                    double cond2 = Math.abs(patientRecords.get(i).getMeasurementValue() + patientRecords.get(i+1).getMeasurementValue());
                    double cond3 = Math.abs(patientRecords.get(i-1).getMeasurementValue() + patientRecords.get(i-2).getMeasurementValue());
                    if(cond1>10 && cond2>10 && cond3>10){
                        triggerAlert(new Alert(patientId,record,timeStamp));
                    }
                }
            }
             if (record.equals("Saturation")) {
                if (measurement < 92) {
                    triggerAlert(new Alert(patientId,record,timeStamp));
                } else {
                   if (Math.abs(endTime - startTime) > 600000) {    //that check if there is at list 10 min of data
                        for(int k = 0; k< ((int) endTime);k++){
                            if (Math.abs(k - ((int) endTime)) <600000) {
                                break;
                            } else {
                                if (Math.abs(patientRecords.get(k).getMeasurementValue() - patientRecords.get(k+600000).getMeasurementValue()) <= 5) {
                                    triggerAlert(new Alert(patientId,record,timeStamp));
                                }
                            }
                        }
                   } 
                }
             }
        }


    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        System.out.println(alert.getCondition());
    }
}
