package com.ecomrse.projectsw.Aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ecomrse.projectsw.security.model.AuthenticationResponse;
import com.ecomrse.projectsw.security.service.JwtService;

@Aspect
@Component
public class RoleCheckAspect {

    private final JwtService jwtService;

    @Autowired
    public RoleCheckAspect(JwtService jwtService) {
        this.jwtService = jwtService;
    }   

    @Before("execution(* com.ecomrse.projectsw.Controllers.*.*(..)) && args(.., token)")
    public void beforeMethodExecution(JoinPoint joinPoint, String token) {
        String userRole = jwtService.extractRoleFromToken(token);
        System.out.println(userRole);
        String requiredRole = checkAuthorization(joinPoint);
        if (!"ALL".equals(requiredRole)) {
            if (!userRole.equals(requiredRole)) {
                System.out.println(userRole);
                System.out.println(requiredRole);
                throw new IllegalArgumentException("You are not authorized to access this resource.");
            } else {
                System.out.println("GOOOOOOOOOOOOOOOOOOOOOOOOOD");
            }
        }
    }

    public String checkAuthorization(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequiredRole requiredRoleAnnotation = method.getAnnotation(RequiredRole.class);
        String requiredRole = requiredRoleAnnotation.value();
        return requiredRole;
    }
}