package com.example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class UserApplicationService {

    @Autowired
    private MessageSource messageSource;

    /**
     * 性別のMapを生成する.
     */
    public Map<String, Integer> getGenderMap(final Locale locale) {
        final Map<String, Integer> genderMap = new LinkedHashMap<>();
        final String male = messageSource.getMessage("male", null, locale);
        final String female = messageSource.getMessage("female", null, locale);
        genderMap.put(male, 1);
        genderMap.put(female, 2);
        return genderMap;
    }
}
