package com.example.Homework_230809.controllers;

import com.example.Homework_230809.services.interfaces.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String greeting(){
        return calculatorService.getGreetingMessage();
    }

    @GetMapping("/plus")
    public Long plus(@RequestParam Integer num1, @RequestParam Integer num2){
        return calculatorService.add(num1, num2);
    }

    @GetMapping("/minus")
    public Long minus(@RequestParam Integer num1, @RequestParam Integer num2) {
        return calculatorService.subtract(num1, num2);
    }

    @GetMapping("/multiply")
    public Long multiply(@RequestParam Integer num1, @RequestParam Integer num2) {
        return calculatorService.multiply(num1, num2);
    }

    @GetMapping("/divide")
    public Double divide(@RequestParam Integer num1, @RequestParam Integer num2) {
        return calculatorService.divide(num1, num2);
    }
}
