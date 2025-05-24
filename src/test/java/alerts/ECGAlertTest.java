package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.BloodPressureAlert;
import com.alerts.ECGAlert;

public class ECGAlertTest {

    @Test
    void instanceOf(){
        Alert alert = new ECGAlert("1", "test", 1);

        assertTrue(alert instanceof ECGAlert);
    }

    @Test 
    void getterTest(){
        Alert alert = new ECGAlert("1", "test", 1);

        assertEquals(alert.getPatientId(), "1");
        assertEquals(alert.getCondition(), "test");
        assertEquals(alert.getTimestamp(), 1);
    }

    @Test
    void equalsTest(){
        Alert alert = new ECGAlert("1", "test", 1);
        
        Alert alertTrue = new ECGAlert("1", "test", 1);
        Alert wrongAlert = new BloodPressureAlert("1", "test", 1);
        Alert alertFalseId = new ECGAlert("2", "test", 1);
        Alert alertFalseCond = new ECGAlert("1", "noTest", 1);
        Alert alertFalseTS = new ECGAlert("1", "test", 2);

        assertTrue(alert.equals(alertTrue));
        assertFalse(alert.equals(wrongAlert));
        assertFalse(alert.equals(alertFalseId));
        assertFalse(alert.equals(alertFalseCond));
        assertFalse(alert.equals(alertFalseTS));
    }

}
