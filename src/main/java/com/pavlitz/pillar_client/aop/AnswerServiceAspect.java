package com.pavlitz.pillar_client.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnswerServiceAspect {

    @Pointcut("execution(public boolean com.pavlitz.pillar_client.services.AnswerService.postAnswer(String, String))")
    private void postAns() {
    }

    @Pointcut("execution(public boolean com.pavlitz.pillar_client.services.AnswerService.checkConnection())")
    private void checkCon() {
    }

    @Pointcut("execution(public * com.pavlitz.pillar_client.services.AnswerService.getWeeklyByType(String))")
    private void weekly(){}

    @Around("postAns()")
    public Object postAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        Object o;
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println("---start method " + name);

        StringBuilder message = new StringBuilder();
        message.append(name);
        for (Object arg : args) {
            message.append("---").append(arg);
        }
        try {
            o = proceedingJoinPoint.proceed();
            if (o instanceof Boolean && (Boolean) o){
                message.insert(0, "---answer WAS sent---");
            }else {
                message.insert(0, "---answer WAS NOT sent---");
            }
            System.out.println(message);
        } catch (Throwable e) {
            message.insert(0, "---Unknown error while posting--- " + e.getMessage());
            System.out.println(message);
            throw e;
        }
        return o;
    }

    @Around("checkCon()")
    public Object checkConnectionAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object o;
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println("---start method " + name);
        try {
            o = proceedingJoinPoint.proceed();
            if(o instanceof Boolean && (Boolean) o){
                System.out.println("---Access granted---");
            }else{
                System.out.println("---Connection refused---");
            }
        } catch (Throwable t) {
            System.out.println("---Unknown error while checking connection--- " + t.getMessage());
            throw t;
        }
        return o;
    }

    @Around("weekly()")
    public Object getWeeklyByTypeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String name = proceedingJoinPoint.getSignature().getName();
        System.out.println("---start method " + name);
        Object o;
        try {
            o = proceedingJoinPoint.proceed();
            System.out.println("---method " + name + " is done---");
        } catch (Throwable throwable) {
            System.out.println("---Unknown error while getting weekly--- " + name);
            throw throwable;
        }
        return o;
    }
}
