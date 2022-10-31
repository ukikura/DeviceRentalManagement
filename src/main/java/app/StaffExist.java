package app;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.validate.StaffExistValidator;

@Documented
@Constraint(validatedBy = StaffExistValidator.class) //この後に作成するバリデーションクラスを指定する
@Target({FIELD}) // 項目に対してバリデーションをかける場合はFIELDを選ぶ
@Retention(RUNTIME)
public @interface StaffExist {

    String message() default "登録されていません"; // エラーメッセージ

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        StaffExist[] value(); // インターフェース名[] value()とする
    }

}