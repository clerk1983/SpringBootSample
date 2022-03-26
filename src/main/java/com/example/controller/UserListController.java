package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller
 * [ユーザー一覧画面]
 */
@Controller
@RequestMapping("/user")
public class UserListController {

    @GetMapping("/list")
    public String getUserList() {
        return "user/list";
    }



}
