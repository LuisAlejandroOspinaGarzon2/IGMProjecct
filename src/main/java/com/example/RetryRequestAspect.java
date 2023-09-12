package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryRequestAspect {

    @Around("@annotation(com.example.RetryRequest)")
    public Object retryRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        return retry(joinPoint);
    }

    @Retryable(
            value = { RuntimeException.class }, // Add any exception types you want to retry
            maxAttemptsExpression = "#{@annotation(com.example.RetryRequest).maxAttempts}",
            backoff = @Backoff(delayExpression = "#{@annotation(com.example.RetryRequest).delayMillis}"))
    private Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
