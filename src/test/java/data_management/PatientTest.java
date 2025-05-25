package data_management;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.data_management.*;
import java.util.List;

public class PatientTest {

    @Test
    void instanceOf(){
        Patient patient = new Patient(0);

        assertTrue(patient instanceof Patient);
    }

    @Test
    void addRecordTest(){
        Patient patient = new Patient(1);

        patient.addRecord(10, "test value", 100);

        List<PatientRecord> patientRecord = patient.getRecords(10, 11);

        PatientRecord testRecordTrue = new PatientRecord(1, 10, "test value", 100);
        PatientRecord testRecordFalse = new PatientRecord(2, 10, "test value", 100);

        assertTrue(patientRecord.get(0).equals(testRecordTrue));
        assertFalse(patientRecord.get(0).equals(testRecordFalse));
    }

    @Test
    void emptyRecord(){
        Patient patient = new Patient(1);

        List<PatientRecord> patientRecord1 = patient.getRecords(10, 11);
        
        assertTrue(patientRecord1.isEmpty());

        patient.addRecord(1, "Test", 10);
        List<PatientRecord> patientRecord2 = patient.getRecords(10, 11);

        assertFalse(patientRecord2.isEmpty());
        
    }
}
