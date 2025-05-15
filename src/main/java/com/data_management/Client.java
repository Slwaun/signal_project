package com.data_management;

import java.net.URI;

import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.client.WebSocketClient;

import com.cardio_generator.outputs.WebSocketOutputStrategy;

public class Client extends WebSocketClient implements DataReader{
    private DataStorage dataStorage;
    private int patientId;
    private long timeStamp;
    private String recordType;
    private double measurementValue;

    public Client(URI uri, DataStorage dataStorage){
        super(uri);
        this.dataStorage = dataStorage;
    }
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected");
    }

    @Override
    public void onMessage(String message) {
        Boolean check = parser(message);
        System.out.println(message);
        if (check) {
           readData(this.dataStorage);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public Boolean parser(String message) {
        String[] content = message.split(",");
        if (content.length != 4) {
             System.err.println("message wrongly formated");
             return false;
        }
        try {
            this.patientId = Integer.parseInt(content[0]);
        }
        catch (Exception e){
            System.err.println("patient Id is NaN");
            return false;
        }
        try {
            this.timeStamp = Long.parseLong(content[1]);
        }
        catch (Exception e){
            System.err.println("timestamp is NaN");
            return false;
        }
        try {
            if (content[2].equals("Alert")) {
                if (content[3].equals("triggered") || content[3].equals("resolved")) {
                    this.recordType = content[2];
                    this.measurementValue = content[3].equals("triggered") ? 1.0 : 0.0;
                }
            }
            if (content[2].equals("Saturation")) {
                if (content[3].indexOf("%") != -1) {
                    this.recordType = content[2];
                    this.measurementValue = Double.parseDouble(content[3].substring(content[3].indexOf("%") - 1));
                }
            }
            if (content[2].equals("ECG") || content[2].equals("DiastolicPressure") || content[2].equals("WhiteBloodCells")
            || content[2].equals("RedBloodCells") || content[2].equals("Cholesterol") || content[2].equals("SystolicPressure")) {
                this.measurementValue = Double.parseDouble(content[3]);
                this.recordType = content[2];

            }
        } catch (Exception e){
            System.err.println("Label not matching data");
            return false;
        }
        if (content[2] == null) {
            System.err.println("label not valid");
            return false;
        }
        return true;
    }
    @Override
    public void readData(DataStorage dataStorage) {

        this.dataStorage.addPatientData(this.patientId, this.measurementValue, this.recordType, this.timeStamp);
        
    }

    public static void main(String[] args) {
        try {
            WebSocketOutputStrategy server = new WebSocketOutputStrategy(Integer.parseInt(args[0]));
            URI uri = new URI("ws://localhost:" + args[0]);
            DataStorage dataStorage = new DataStorage();
            Client client = new Client(uri, dataStorage);
            client.connect();
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
    
    
}
