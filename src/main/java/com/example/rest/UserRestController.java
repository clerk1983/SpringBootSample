package com.example.rest;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import com.example.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MessageSource messageSource;

    /**
     * ユーザーを登録
     */
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class) final SignupForm form,
                                 final BindingResult bindingResult, final Locale locale) {

        // 入力チェック
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, e -> messageSource.getMessage(e, locale)));
            return new RestResult(90, errors);
        }

        final MUser user = modelMapper.map(form, MUser.class);
        userService.signup(user);
        return new RestResult(0, null);
    }


    @PutMapping("/update")
    public int updateUser(final UserDetailForm form) {
        // ユーザーを更新
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
        return 0;
    }

    @DeleteMapping("/delete")
    public int deleteUser(final UserDetailForm form) {
        // ユーザーを削除
        userService.deleteUserOne(form.getUserId());
        return 0;
    }


}
