package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
        public NotFoundException() {
    }
}
