package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class DataReaderFile implements DataReader {
    private String outputDir;

    /**
     * Construct a instance of DataReaderFile and create a path to a file
     * 
     * @param args arguments when the program is launched
     */    
    public DataReaderFile(String[] args){
        this.outputDir = "output/SystolicPressure.txt";
        /*
        for(String arg : args){
            if(arg.startsWith("--output")){
                int indexStart = arg.indexOf("<");
                int indexEnd = arg.indexOf(">");
                if(indexStart != -1){
                    String path = arg.substring(indexStart, indexEnd-1);
                    String[] pathPart = path.split("_");
                    this.outputDir = pathPart[0] + "/" + pathPart[1];
                }
            }
        }
         */
    }

    /**
     * Create a file based on a path and read it to stock the data into a DataStorage
     * 
     * @param dataStorage an instance of DataStorage
     * @throws
     */
    public void readData(DataStorage dataStorage) throws IOException{
        // Create a File
        File file = new File(this.outputDir);
        
        // Buffer that will read every line of the File and store the data
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;

            // loop that will stop when there will be no line to read anymore
            while ((line = reader.readLine()) != null) {
                // Split the line everytime there is a "," in the line and put it in a array
                String[] lineParts = line.split(",");

                // Convert every value from String to their supposed type
                int patientId = Integer.parseInt(lineParts[0].substring(lineParts[0].indexOf(":")+2));
                long timeStamp = Long.parseLong(lineParts[1].substring(lineParts[1].indexOf(":")+2));
                String recordType = lineParts[2].substring(lineParts[2].indexOf(":")+2);

                // Convert this String into a Double based on what is in the String
                Double measurementValue;
                // if the String contains a "%" then we will only takes what before the "%" and what's after "Data:" 
                if(lineParts[3].indexOf("%") != -1){
                    measurementValue = Double.parseDouble(lineParts[3].substring(lineParts[3].indexOf("Data: "), lineParts[3].indexOf("%") - 1));
                } else {
                    String condition = lineParts[3].substring(7);
                    // Check if the value is the word triggered or resolved to set a boolean value with a 1 or 0
                    // else convert the value into a Double
                    if(condition.equals("triggered")){
                        measurementValue = 1.0;
                    } else if(condition.equals("resolved")) {
                        measurementValue = 0.0;
                    } else {
                        measurementValue = Double.parseDouble(condition);
                    }
                    
                }

                // if all the data needed is there, add them to the DataStorage
                if(lineParts.length >= 3){
                    dataStorage.addPatientData(patientId, measurementValue, recordType, timeStamp);
                }
            }
        } catch(IOException e){

        }
    }
}
