package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller
 * ログイン画面表示
 */
@Controller
public class LoginController {

    /**
     * ログイン画面を表示
     */
    @GetMapping(value = "/login")
    public String getLogin() {
        return "login/login";
    }

    /**
     * ユーザー一覧画面にリダイレクト
     * @return
     */
    @PostMapping(value="/login")
    public String postLogin() {
        return "redirect:/user/list";
    }


}
