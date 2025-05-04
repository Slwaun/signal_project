package com.cardio_generator.generators;

// Delete the space between the import
import com.cardio_generator.outputs.OutputStrategy;
import java.util.Random;

/**
 * Generate an alert for a group of patients
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    // Change the name of AlertStates to alertStates
    private boolean[] alertStates; // False = resolved, true = pressed.

    /**
     * Create an alert generator for the number of patient given
     * @param patientCount the number of patients, it has to be higher or equal to 0
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    @Override
    /**
     * Checked the value of alertStates of the patient and use outputStrategy depending of the value to output an alerts
     * @param patientId the Id of the patient
     * @param outputStrategy the selected outputStrategy
     */
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Change the name of Lambda to lambda
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    // Line-wrapping the outputStrategy because the line contains more than 100 characters
                    outputStrategy.output(
                        patientId, System.currentTimeMillis(), "Alert", "triggered"
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
