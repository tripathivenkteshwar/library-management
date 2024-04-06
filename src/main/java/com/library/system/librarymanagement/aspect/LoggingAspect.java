package com.library.system.librarymanagement.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("(@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)) " +
                "&& @annotation(com.library.system.librarymanagement.aspect.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        final StopWatch stopWatch = new StopWatch();
        log.info("Starting execution method {}", methodName);
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        log.info("Finished execution method {}", methodName);
        log.info("Execution time of "+ methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}