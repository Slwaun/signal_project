package alerts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.BloodOxygenAlert;
import com.alerts.BloodOxygenAlertFactory;

public class BloodOxygenAlertFactoryTest {

    @Test
    void createAlertTest(){
        BloodOxygenAlertFactory factory = new BloodOxygenAlertFactory();
        Alert alert = factory.createAlert("1", "test", 10);

        Alert alertTest = new BloodOxygenAlert("1", "test", 10);

        assertTrue(alert.equals(alertTest));
    }

}
