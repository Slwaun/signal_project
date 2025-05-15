package com;

import java.io.IOException;


import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;
import com.data_management.Client;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("DataStorage")) {
            System.out.println("Data");
            DataStorage.main(new String[]{});
        } 
        else if (args.length > 0 && args[0].equals("Client")) {
            System.out.println("Client");
            Client.main(new String[]{args[1]});
        }
         else {
            try {
                System.out.println("Health");
                HealthDataSimulator.main(new String[]{});
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
