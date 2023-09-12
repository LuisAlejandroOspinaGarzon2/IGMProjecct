package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RetryRequest {
    int maxAttempts() default 3; // Adjust the number of retry attempts as needed
    long delayMillis() default 1000; // Adjust the initial delay in milliseconds
}
