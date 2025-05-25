package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {

    public RepeatedAlertDecorator(Alert alert) {
        super(alert);
    }

    public boolean checkCondition(){
        String condition = getCondition();
        boolean check = false;

        long start = 0;
        long period = 5;

        for(long i = start; i < period; i++){
            if(condition.equals(getCondition()) && check == false){
                check = changeCheck(check);
            }
        }

        return check;
    }

    private boolean changeCheck(boolean bol){
        return !bol;
    }

}
