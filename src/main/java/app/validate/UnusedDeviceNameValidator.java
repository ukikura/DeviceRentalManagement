package app.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import app.UnusedDeviceName;
import app.entity.Devices;
import app.service.DeviceService;


public class UnusedDeviceNameValidator implements ConstraintValidator<UnusedDeviceName, String> {

    @Autowired
    private DeviceService service;

    public void initialize(UnusedDeviceName constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        Devices device = service.findByDeviceNameOptional(value).orElse(null); // valueは入力値
        if(device == null){
            return true;
        } 
        return false;
    }
}