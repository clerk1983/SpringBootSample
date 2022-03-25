package com.example.controller;

import com.example.application.service.UserApplicationService;
import com.example.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Map;

/**
 * Controller
 * [新規登録]
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired
    private UserApplicationService userApplicationService;

    @GetMapping("/signup")
    public String getSignup(final Model model, final Locale locale,
                            @ModelAttribute SignupForm signupForm) {
        // 性別を取得
        final Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute SignupForm signupForm) {
        log.info(signupForm.toString());
        return "redirect:/login";
    }

}
