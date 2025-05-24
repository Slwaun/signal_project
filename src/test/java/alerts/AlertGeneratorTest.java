package alerts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alerts.AlertGenerator;
import com.data_management.DataStorage;

public class AlertGeneratorTest {

    @Test
    void instanceOf(){
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());

        assertTrue(generator instanceof AlertGenerator);

    }

}
