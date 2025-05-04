package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory; //change of B into b

    public static final ConcurrentHashMap<String, String> FILE_MAP = new ConcurrentHashMap<>(); //add of static because is a final and change of name to FILE_MAP because is a final variable

    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory; //change of B to b
    }

    @Override
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