package com.example.application.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserApplicationService {

    /**
     * 性別のMapを生成する.
     */
    public Map<String, Integer> getGenderMap() {
        final Map<String, Integer> genderMap = new LinkedHashMap<>();
        genderMap.put("男性", 1);
        genderMap.put("女性", 2);
        return genderMap;
    }
}
