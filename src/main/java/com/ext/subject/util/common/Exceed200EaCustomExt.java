package com.ext.subject.util.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidExceed200EaValidator.class)
public @interface Exceed200EaCustomExt {
	String message() default "최대 200개까지만 등록 가능합니다";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
