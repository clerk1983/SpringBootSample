package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * サービス実行前にログを出力
     * 対象:[UserService]をクラスに含んでいる.
     * @param jp
     */
    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint jp) {
        final long id = Thread.currentThread().getId();
        log.info("メソッド開始:[" + id +"]"+ jp.getSignature());
    }

    /**
     * サービス実行後にログを出力
     * 対象:[UserService]をクラスに含んでいる.
     * @param jp
     */
    @After("execution(* *..*.*UserService.*(..))")
    public void endLog(JoinPoint jp) {
        final long id = Thread.currentThread().getId();
        log.info("メソッド終了:[" + id +"]" + jp.getSignature());
    }

    /**
     * コントローラの実行前後にログを出力する
     */
    // @Around("bean(*Controller)")
    // @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        // 開始ログ
        final long id = Thread.currentThread().getId();
        log.info("メソッド開始:【" + id +"】"+ jp.getSignature());
        try {
            final Object result = jp.proceed();
            log.info("メソッド終了:【" + id +"】"+ jp.getSignature());
            return result;
        } catch (Exception e) {
            // エラーログ
            log.error("Error:【" + id +"】"+ jp.getSignature());
            throw e;
        }

    }


}
