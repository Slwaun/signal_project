package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generate the data of patient
 */
public interface PatientDataGenerator {
    /**
     * Generate an output based on the Id of a patient
     * 
     * @param patientId the Id of a patient
     * @param outputStrategy an outputStrategy
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
