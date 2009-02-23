package com.wcmda.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import com.wcmda.validator.impl.*;
import org.hibernate.validator.ValidatorClass;

@ValidatorClass(UniqueUserName.class)
@Target(METHOD)
@Retention(RUNTIME)
@Documented

public @interface UniqueUser {

	Unique type() default Unique.FIRSTNAME;
	String message() default "The username is taken, please choose another one";
	Unique value();
	


}
