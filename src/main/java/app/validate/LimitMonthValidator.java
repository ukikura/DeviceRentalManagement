package app.validate;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.LimitMonth;


public class LimitMonthValidator implements ConstraintValidator<LimitMonth, Date> {

	private int month;

    public void initialize(LimitMonth constraintAnnotation) {
    	month = constraintAnnotation.month();
    }

    public boolean isValid(Date value, ConstraintValidatorContext context) {
    	if(value==null) {
    		return true;
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.MONTH, month);
    	Date limitDate = calendar.getTime();
    	if(value.before(limitDate)) {
    		return true;
    	} else {
            return false;
        }
    }
}