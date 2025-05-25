package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.AlertDecorator;
import com.alerts.BloodOxygenAlert;
import com.alerts.RepeatedAlertDecorator;

public class RepeatedAlertDecoratorTest {

    @Test
    void instanceOf(){
        AlertDecorator decorator = new RepeatedAlertDecorator(null);

        assertTrue(decorator instanceof RepeatedAlertDecorator);
    }

    @Test
    void checkConditionTest(){
        RepeatedAlertDecorator decorator = new RepeatedAlertDecorator(new BloodOxygenAlert("1", "Test", 1));

        assertTrue(decorator.checkCondition());
    }

    @Test
    void getterTest(){
        RepeatedAlertDecorator decorator = new RepeatedAlertDecorator(new BloodOxygenAlert("1", "Test", 1));

        assertEquals(decorator.getPatientId(), "1");
        assertEquals(decorator.getCondition(), "Test");
        assertEquals(decorator.getTimestamp(), 1);
    }

}
