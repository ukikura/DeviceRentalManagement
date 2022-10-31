package app.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import app.StaffExist;
import app.entity.Users;
import app.service.UserService;


public class StaffExistValidator implements ConstraintValidator<StaffExist, String> {

    @Autowired
    private UserService service;

    public void initialize(StaffExist constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
    	if(value.length()<10) {
    		return true;
    	}
        Users user = service.findByStaffIdOptional(value).orElse(null); // valueは入力値
        if(user == null){
            return false;
        } 
        return true;
    }
}