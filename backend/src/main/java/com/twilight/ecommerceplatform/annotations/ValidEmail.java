package com.twilight.ecommerceplatform.annotations;

import com.twilight.ecommerceplatform.validator.emailValidator;
import com.twilight.ecommerceplatform.validator.mobileNumberValidator;
import jakarta.validation.Constraint;
import jdk.jfr.Description;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;

import java.lang.annotation.*;

@MetadataDefinition
@Name("Email validity")
@Description("Must follow email format")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = emailValidator.class)
@Documented
public @interface ValidEmail {
}
