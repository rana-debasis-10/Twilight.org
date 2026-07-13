package com.twilight.validators;

import com.twilight.utils.annotations.MobileNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {
    /// Mobile Number start with [6-9] and then can have any number from 0 to 9 nine times
    private static final String MOBILE_PATTERN = "^[6-9]\\d{9}$";

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        if(mobileNumber == null || mobileNumber.isBlank()) {
            return false;
        }
        return Pattern.matches(MOBILE_PATTERN, mobileNumber);
    }
}
