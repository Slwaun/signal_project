package alerts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.ECGAlert;
import com.alerts.ECGAlertFactory;

public class ECGAlertFactoryTest {

    @Test
    void createAlertTest(){
        ECGAlertFactory factory = new ECGAlertFactory();
        Alert alert = factory.createAlert("1", "test", 10);

        Alert alertTest = new ECGAlert("1", "test", 10);

        assertTrue(alert.equals(alertTest));
    }

}
