package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelloService {

    @Autowired
    private HelloRepository repository;

    public Employee getEmployee(final String id) {
        final Map<String, Object> map = repository.findById(id);
        final String employeeId = (String) map.get("id");
        final String name = (String) map.get("name");
        final int age = (Integer) map.get("age");
        return new Employee(employeeId, name, age);
    }

}
