package app;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.validate.NotPastValidator;

@Documented
@Constraint(validatedBy = NotPastValidator.class) 
@Target({FIELD})
@Retention(RUNTIME)
public @interface NotPast {

    String message() default "過去の日付です"; 
    

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        NotPast[] value();
    }

}