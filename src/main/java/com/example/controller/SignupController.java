package com.example.controller;

import com.example.application.service.UserApplicationService;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/signup")
    public String getSignup(final Model model, final Locale locale,
                            @ModelAttribute SignupForm signupForm) {
        // 性別を取得
        final Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    @PostMapping("/signup")
    public String postSignup(final Model model,
                             final Locale locale,
                             @ModelAttribute @Validated(GroupOrder.class) final SignupForm signupForm,
                             final BindingResult bindingResult) {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            // [新規登録]画面に戻る
            return getSignup(model, locale, signupForm);
        }
        log.info(signupForm.toString());

        // Form を MUserクラスに変換
        final MUser user = modelMapper.map(signupForm, MUser.class);

        // ユーザー登録
        userService.signup(user);

        return "redirect:/login";
    }

}
