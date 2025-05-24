package alerts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategy;
import com.alerts.BloodPressureStrategy;

public class BloodPressureStrategyTest {

    @Test
    void checkAlertTest(){
        String condition1 = "DiastolicPressure";
        String condition2 = "SystolicPressure";
        String condition3 = "false";

        AlertStrategy strategy = new BloodPressureStrategy();

        assertTrue(strategy.checkAlert(condition1));
        assertTrue(strategy.checkAlert(condition2));
        assertFalse(strategy.checkAlert(condition3));
    }

}
