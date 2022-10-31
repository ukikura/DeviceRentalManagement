package app.validate;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.NotPast;


public class NotPastValidator implements ConstraintValidator<NotPast, Date> {


    public void initialize(NotPast constraintAnnotation) {
    }
    public boolean isValid(Date value, ConstraintValidatorContext context) {
    	if(value==null) {
    		return true;
    	}

    	Calendar todayCalendar = Calendar.getInstance();
    	todayCalendar.setTime(value);
    	todayCalendar.add(Calendar.DATE, 1);
    	Date valuetomorrow = todayCalendar.getTime();
    	if(valuetomorrow.after(new Date())) {
    		return true;
    	} else {
            return false;
        }
    }
}