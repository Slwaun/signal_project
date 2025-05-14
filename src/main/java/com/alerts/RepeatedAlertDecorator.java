package com.alerts;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RepeatedAlertDecorator extends AlertDecorator {

    public RepeatedAlertDecorator(Alert alert) {
        super(alert);
    }

    public boolean checkCondition(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        String condition = getCondition();
        boolean check = false;

        long start = 0;
        long period = 5;

        scheduler.scheduleAtFixedRate(() -> {
            if(condition.equals(getCondition())){
                changeCheck(check);
            }
        }, start, period, TimeUnit.MILLISECONDS);

        return check;
    }

    private boolean changeCheck(boolean bol){
        return !bol;
    }

}
