package com.example.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalContorolAdvice {

    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(final DataAccessException e, final Model model) {
        // 空文字をセット
        model.addAttribute("error", "");
        // メッセージをセット
        model.addAttribute("message", "SignupControllerで例外発生");
        // HTTPのエラーコード(500)を設定
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";

    }
}
