package com.cardio_generator.outputs;

/**
 *  Give the output of a patients
 */
public interface OutputStrategy {
    /**
     * Give a output that contain the data of the patients.
     * 
     * @param patientId is a integer that represent the id of a patient.
     * @param timestamp is the record of the date and time of the patient
     * @param label is a string that contain the name of the file that will be generated
     * @param data is a string that contain the data related to the patient
     */
    void output(int patientId, long timestamp, String label, String data);
}
