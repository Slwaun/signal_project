package alerts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategy;
import com.alerts.HeartRateStrategy;

public class HeartRateStrategyTest {

    @Test
    void checkAlertTest(){
        String condition1 = "";
        String condition2 = "false";

        AlertStrategy strategy = new HeartRateStrategy();

        assertTrue(strategy.checkAlert(condition1));
        assertFalse(strategy.checkAlert(condition2));
    }

}
