package com.example.controller;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserDetailController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/detail/{userId:.+}")
    public String getUser(final Model model,
                          @PathVariable("userId") final String userId) {

        final MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        final UserDetailForm form = modelMapper.map(user, UserDetailForm.class);
        model.addAttribute("userDetailForm", form);

        return "user/detail";
    }

    @PostMapping(value="/detail", params = "update")
    public String updateUser(final UserDetailForm form, final Model model) {
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
        return "redirect:/user/list";
    }

    @PostMapping(value="/detail", params = "delete")
    public String deleteUser(final UserDetailForm form, final Model model) {
        userService.deleteUserOne(form.getUserId());
        return "redirect:/user/list";
    }
}