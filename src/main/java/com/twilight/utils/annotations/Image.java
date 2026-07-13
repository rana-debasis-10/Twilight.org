package com.twilight.utils.annotations;

import com.twilight.validators.ImageValidator;
import jakarta.validation.Constraint;
import jdk.jfr.Description;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;

import java.lang.annotation.*;

@MetadataDefinition
@Name("Image validity")
@Description("Must be an Image")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = ImageValidator.class)
@Documented
public @interface Image {
}
