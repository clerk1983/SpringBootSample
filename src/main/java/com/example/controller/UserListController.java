package com.example.controller;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller
 * [ユーザー一覧画面]
 */
@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * ユーザー一覧取得
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String getUserList(@ModelAttribute final UserListForm form,
                              final Model model) {
        final MUser user = modelMapper.map(form, MUser.class);
        final List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    /**
     * ユーザー検索
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/list")
    public String postUserList(@ModelAttribute final UserListForm form,
                               final Model model) {
        final MUser user = modelMapper.map(form, MUser.class);
        final List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }

}
