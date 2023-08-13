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
    public Long add(Integer num1, Integer num2) {
        return (long)num1 + num2;
    }

    @Override
    public Long subtract(Integer num1, Integer num2) {
        return (long)num1 - num2;
    }

    @Override
    public Long multiply(Integer num1, Integer num2) {
        return (long)num1 * num2;
    }

    @Override
    public Double divide(Integer num1, Integer num2) {
        if (num2 == 0){
            throw new IllegalArgumentException("Oops! Divided by zero!");
        }
        return (double)num1 / num2;
    }
}
