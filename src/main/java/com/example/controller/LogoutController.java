package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller
 * [ログアウト]
 */
@Controller
@Slf4j
public class LogoutController {

    /**
     * ログイン画面にリダイレクト
     */
    @PostMapping("/logout")
    public String postLogout() {
        log.info("LOGOUT");
        return "redirect:/login";
    }

}
