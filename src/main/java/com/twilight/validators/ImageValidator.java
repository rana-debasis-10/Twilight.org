package com.twilight.validators;

import com.twilight.utils.annotations.Image;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    @Autowired
    private Tika tika;

    private final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/jpg",
            "image/svg",
            "image/png",
            "image/webp"
    );

    @Override
    public void initialize(Image constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String mimeType = null;
        try {
            mimeType = tika.detect(value.getInputStream());
        } catch (IOException e) {
            return false;
        }
        return mimeType != null && ALLOWED_TYPES.contains(mimeType);
    }
}
