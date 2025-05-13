package com;

import java.io.IOException;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("DataStorage")) {
            DataStorage.main(new String[]{});
        } else {
            try {
                HealthDataSimulator.main(new String[]{});
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
