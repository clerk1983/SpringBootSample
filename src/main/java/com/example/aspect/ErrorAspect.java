package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ErrorAspect {

    @AfterThrowing(value = "execution(* *..*..*(..))&&(bean(*Controller) || bean(*Service) || bean(*Repository))"
                  ,throwing = "e")
    public void throwingNull(DataAccessException e) {
        // 例外処理
        log.error("DataAccessExceptionが発生");
    }



}
