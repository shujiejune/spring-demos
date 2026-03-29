package org.example.employeemanagement.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect      // 1. Tells Spring this class contains AOP logic
@Component   // 2. Tells Spring to manage this class as a Bean
@Slf4j       // 3. Gives us our Logger
public class LoggingAspect {
    /**
     * @Around is the "Advice".
     * The string inside is the "Pointcut Expression".
     * It says: "Intercept any return type (*), inside the service package,
     * any class (*), any method (*), with any number of arguments (..)"
     */
    @Around("execution(* org.example.employeemanagement.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        // Code here runs BEFORE the service method
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        log.info("AOP: Starting execution of {}", methodName);

        Object result;
        try {
            // This line actually executes the EmployeeService method (e.g., createEmployee)
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            // Code here runs if the service throws an Exception
            log.error("AOP: Exception in {} - {}", methodName, ex.getMessage());
            throw ex; // Re-throw so the GlobalExceptionHandler can catch it!
        }

        // Code here runs AFTER the service method successfully finishes
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("AOP: Finished {} in {} ms", methodName, timeTaken);

        // Must return the result back to the Controller!
        return result;
    }
}
