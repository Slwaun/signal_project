package alerts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategy;
import com.alerts.OxygenSaturationStrategy;

public class OxygenSaturationStrategyTest {

    @Test
    void checkAlertTest(){
        String condition1 = "Saturation";
        String condition2 = "false";

        AlertStrategy strategy = new OxygenSaturationStrategy();

        assertTrue(strategy.checkAlert(condition1));
        assertFalse(strategy.checkAlert(condition2));
    }

}
