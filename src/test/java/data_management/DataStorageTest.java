package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.data_management.DataReader;
import com.data_management.DataReaderFile;
import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.util.List;

class DataStorageTest {

    @Test
    void testAddAndGetRecords() {
        // TODO Perhaps you can implement a mock data reader to mock the test data?
        // DataReader reader
        //DataReader reader = new DataReader();
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }

    @Test
    void instanceOf(){
        DataReader reader = new DataReaderFile(null);
        DataStorage storage = new DataStorage();
        DataStorage storageReader = new DataStorage(reader);

        assertTrue(storage instanceof DataStorage);
        assertTrue(storageReader instanceof DataStorage);
    }

    @Test
    void emptyPatient(){
        DataStorage storage = new DataStorage();

        List<PatientRecord> records = storage.getRecords(1, 0, 100);
        assertTrue(records.isEmpty());
    }
}
