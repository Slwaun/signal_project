package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class DataReaderFile implements DataReader {
    private String outputDir;

    public DataReaderFile(String[] args){
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
    }

    public void readData(DataStorage dataStorage) throws IOException{
        File file = new File(this.outputDir);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;

            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(",");

                int patientId = Integer.parseInt(lineParts[0].substring(12));
                long timeStamp = Long.parseLong(lineParts[1].substring(12));
                String recordType = lineParts[2].substring(6);
                Double measurementValue;
                if(lineParts[3].indexOf("%") == -1){
                    measurementValue = Double.parseDouble(lineParts[3].substring(7, lineParts[3].indexOf("%") - 1));
                } else {
                    measurementValue = Double.parseDouble(lineParts[3].substring(7));
                }

                if(lineParts.length >= 3){
                    dataStorage.addPatientData(patientId, measurementValue, recordType, timeStamp);
                }
            }
        } catch(IOException e){

        }
    }
}
