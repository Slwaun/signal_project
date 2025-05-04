package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Generate a file that will contain the information about the patienId.
 *  This class is responsible of the path of the file and to add the data in the file
 */

public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory; //change of B into b

    public static final ConcurrentHashMap<String, String> FILE_MAP = new ConcurrentHashMap<>(); //add of static because is a final and change of name to FILE_MAP because is a final variable

    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory; //change of B to b
    }

    @Override
    /**
     * Generate a file in a pecific path with the data of the patients.
     * 
     * @param patientId is a integer that represent the id of a patient.
     * @param timestamp is the record of the date and time of the patient
     * @param label is a string that contain the name of the file that will be generated
     * @param data is a string that contain the data related to the patient
     * 
     * @throws exception if the code is unable to write or acces the file in the indecated path 
     */
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        }   catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        String filePath = FILE_MAP.computeIfAbsent(     //change of FilePath to filePath
                label, k -> Paths.get(baseDirectory, label + ".txt").toString()); //correction the identation to respect the guidlines

        // Write the data to the file
        try (PrintWriter out =                                                                      // change of the indentation to respect the guidline because
            new PrintWriter(                                                                        // the continous ligne contains to much and we indent to make the
                Files.newBufferedWriter(                                                            // code more readable
                    Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {  //
            out.printf(                                                                             //
                "Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n",                             //
                patientId, timestamp, label, data);                                                 //
        } catch (Exception e) {                                                                     //
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}