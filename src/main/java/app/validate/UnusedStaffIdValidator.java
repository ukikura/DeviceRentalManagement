package app.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import app.UnusedStaffId;
import app.entity.Users;
import app.service.UserService;


public class UnusedStaffIdValidator implements ConstraintValidator<UnusedStaffId, String> {

    @Autowired
    private UserService service;

    public void initialize(UnusedStaffId constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        Users user = service.findByStaffIdOptional(value).orElse(null); // valueは入力値
        if(user == null){
            return true;
        } 
        return false;
    }
}