package alerts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.alerts.AlertDecorator;
import com.alerts.BloodOxygenAlert;
import com.alerts.Alert;
import com.alerts.BloodPressureAlert;
import com.alerts.NurseAlert;
import com.alerts.PriorityAlertDecorator;
import com.data_management.PatientRecord;

public class PriorityAlertDecoratorTest {

    @Test
    void instanceOf(){
        AlertDecorator decorator = new PriorityAlertDecorator(null);

        assertTrue(decorator instanceof PriorityAlertDecorator);
    }

    @Test
    void priorityListTest(){
        ArrayList<PatientRecord> list = new ArrayList<>();

        // non priority alert
        list.add(new PatientRecord(1, 100, "SystolicPressure", 100));
        list.add(new PatientRecord(2, 100, "DiastolicPressure", 100));
        list.add(new PatientRecord(3, 94, "Saturation", 100));

        // second priority alert
        list.add(new PatientRecord(4, 1, "Alert", 100));

        // first priority alert
        list.add(new PatientRecord(5, 190, "SystolicPressure", 100));
        list.add(new PatientRecord(6, 80, "SystolicPressure", 100));
        list.add(new PatientRecord(7, 130, "DiastolicPressure", 100));
        list.add(new PatientRecord(8, 50, "DiastolicPressure", 100));
        list.add(new PatientRecord(9, 90, "Saturation", 100));

        // non alert
        list.add(new PatientRecord(10, 95, "Saturation", 100));
        list.add(new PatientRecord(11, 0, "Alert", 100));

        Alert alert1 = new BloodPressureAlert("5", "SystolicPressure", 100);
        Alert alert2 = new BloodPressureAlert("6", "SystolicPressure", 100);
        Alert alert3 = new BloodPressureAlert("7", "DiastolicPressure", 100);
        Alert alert4 = new BloodPressureAlert("8", "DiastolicPressure", 100);
        Alert alert5 = new BloodOxygenAlert("9", "Saturation", 100);

        Alert alert6 = new NurseAlert("4", "Alert", 100);

        Alert alert7 = new BloodPressureAlert("1", "SystolicPressure", 100);
        Alert alert8 = new BloodPressureAlert("2", "DiastolicPressure", 100);
        Alert alert9 = new BloodOxygenAlert("3", "Saturation", 100);

        ArrayList<Alert> alertList = PriorityAlertDecorator.priorityAlert(list);

        assertTrue(alert1.equals(alertList.get(0)));
        assertTrue(alert2.equals(alertList.get(1)));
        assertTrue(alert3.equals(alertList.get(2)));
        assertTrue(alert4.equals(alertList.get(3)));
        assertTrue(alert5.equals(alertList.get(4)));
        assertTrue(alert6.equals(alertList.get(5)));
        assertTrue(alert7.equals(alertList.get(6)));
        assertTrue(alert8.equals(alertList.get(7)));
        assertTrue(alert9.equals(alertList.get(8)));

    }
    
}
