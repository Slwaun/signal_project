package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.data_management.PatientRecord;

public class PatientRecordTest {

    @Test
    void instanceOf(){
        PatientRecord testRecord = new PatientRecord(1, 10, "test value", 100);

        assertTrue(testRecord instanceof PatientRecord);
    }

    @Test
    void getterTest(){
        PatientRecord testRecord = new PatientRecord(1, 10, "test value", 100);

        assertEquals(testRecord.getPatientId(), 1);
        assertEquals(testRecord.getMeasurementValue(), 10);
        assertEquals(testRecord.getRecordType(), "test value");
        assertEquals(testRecord.getTimestamp(), 100);
    }

    @Test
    void equalsTest(){
        PatientRecord testRecord = new PatientRecord(1, 10, "test value", 100);

        PatientRecord testRecordTrue = new PatientRecord(1, 10, "test value", 100);
        PatientRecord testRecordFalseId = new PatientRecord(2, 10, "test value", 100);
        PatientRecord testRecordFalseMV = new PatientRecord(1, 9, "test value", 100);
        PatientRecord testRecordFalseRT = new PatientRecord(1, 10, "Test Value", 100);
        PatientRecord testRecordFalseTS = new PatientRecord(1, 10, "test value", 200);


        assertTrue(testRecord.equals(testRecordTrue));
        assertFalse(testRecord.equals(testRecordFalseId));
        assertFalse(testRecord.equals(testRecordFalseMV));
        assertFalse(testRecord.equals(testRecordFalseRT));
        assertFalse(testRecord.equals(testRecordFalseTS));
    }

}
