package com.renanwillian.service.exception;

import java.util.function.Supplier;

public class NoContentException extends RuntimeException {
    public static Supplier<NoContentException> build() {
        return NoContentException::new;
    }
}