package com.revature.aspects;

import com.revature.dtos.DecodedJwtDTO;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Component
@Aspect
public class SecurityAspect {
    private Logger logger = Logger.getLogger(SecurityAspect.class);

    @Around("controllerMethodsPointCut()")
    public Object verifyJwt(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String auth = request.getHeader("Authorization");

        WebClient webClient = WebClient.create(System.getenv("AUTH_SERVER"));
        try{
            DecodedJwtDTO decodedJwtDTO = webClient
                    .post()
                    .body(Mono.just(auth), String.class)
                    .retrieve()
                    .onStatus(httpStatus -> HttpStatus.UNAUTHORIZED.equals(httpStatus),
                            clientResponse -> {
                                Mono.empty();
                                return null;
                            })
                    .bodyToMono(DecodedJwtDTO.class).block();
            if (decodedJwtDTO.getId() != 0){
                logger.info("JWT verified: " + decodedJwtDTO);
                Object obj = pjp.proceed();
                return obj;
            }
        }catch(Exception e){
            logger.error("Unable to verify JWT");
        }
        return null;
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void controllerMethodsPointCut(){}
}
