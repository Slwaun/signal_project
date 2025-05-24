package alerts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.BloodPressureAlert;
import com.alerts.BloodPressureAlertFactory;

public class BloodPressureAlertFactoryTest {

    @Test
    void createAlertTest(){
        BloodPressureAlertFactory factory = new BloodPressureAlertFactory();
        Alert alert = factory.createAlert("1", "test", 10);

        Alert alertTest = new BloodPressureAlert("1", "test", 10);

        assertTrue(alert.equals(alertTest));
    }

}
