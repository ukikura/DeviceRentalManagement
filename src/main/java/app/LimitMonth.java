package app;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.validate.LimitMonthValidator;

@Documented
@Constraint(validatedBy = LimitMonthValidator.class) 
@Target({FIELD})
@Retention(RUNTIME)
public @interface LimitMonth {

    String message() default "期間外です"; 
    
    int month() default 1;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        LimitMonth[] value();
    }

}