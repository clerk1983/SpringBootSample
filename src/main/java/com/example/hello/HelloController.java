package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    private HelloService service;

    @GetMapping(value = "/hello")
    public String getHello() {
        return "hello";
    }

    @PostMapping(value = "/hello")
    public String postRequest(@RequestParam("text1") final String str, final Model model) {
        model.addAttribute("sample", str);
        return "hello/response";
    }

    @PostMapping(value = "/hello/db")
    public String postDbRequest(@RequestParam("text2") final String id, final Model model) {
        final Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "hello/db";
    }

}
