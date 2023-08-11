package com.example.Homework_230809.services.implementations;

import com.example.Homework_230809.services.interfaces.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public String getGreetingMessage() {
        return "Добро пожаловать в калькулятор";
    }

    @Override
    public Integer add(Integer num1, Integer num2) {
        return num1 + num2;
    }

    @Override
    public Integer subtract(Integer num1, Integer num2) {
        return num1 - num2;
    }

    @Override
    public Integer multiply(Integer num1, Integer num2) {
        return num1 * num2;
    }

    @Override
    public Integer divide(Integer num1, Integer num2) {
        if (num2 == 0){
            throw new IllegalArgumentException("Oops! Divided by zero!");
        }
        return num1 / num2;
    }
}
