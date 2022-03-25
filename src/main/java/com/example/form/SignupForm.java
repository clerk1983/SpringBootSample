package com.example.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record SignupForm(String userId, String password, String userName,
                         @DateTimeFormat(pattern = "yyyy/MM/dd") Date birthday,
                         Integer age, Integer gender) {
}
