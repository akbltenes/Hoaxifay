package com.hoaxify.web.service.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEmailValidator.class) //validationu nerede donecegiz
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE }) //nerede kullanılabilir
@Retention(RUNTIME)//hangi asamada kullaılacak

public @interface UniqueEmail {

    String message() default "E-mail zaten kulanımda";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
