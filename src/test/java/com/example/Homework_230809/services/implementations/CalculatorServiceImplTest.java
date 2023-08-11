package com.example.Homework_230809.services.implementations;

import com.example.Homework_230809.services.interfaces.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {
    private final CalculatorService sut = new CalculatorServiceImpl();

    public static Stream<Arguments> provideParametersForAdditionTests() {
        return Stream.of(
                Arguments.of(TestData.NUM1, TestData.NUM1, TestData.ADDITION_RESULT11),

                Arguments.of(TestData.NUM1, TestData.NUM2, TestData.ADDITION_RESULT12),
                Arguments.of(TestData.NUM2, TestData.NUM1, TestData.ADDITION_RESULT12),

                Arguments.of(TestData.NUM1, TestData.NUM3, TestData.ADDITION_RESULT13),
                Arguments.of(TestData.NUM3, TestData.NUM1, TestData.ADDITION_RESULT13),

                Arguments.of(TestData.NUM2, TestData.NUM2, TestData.ADDITION_RESULT22),

                Arguments.of(TestData.NUM2, TestData.NUM3, TestData.ADDITION_RESULT23),
                Arguments.of(TestData.NUM3, TestData.NUM2, TestData.ADDITION_RESULT23),

                Arguments.of(TestData.NUM3, TestData.NUM3, TestData.ADDITION_RESULT33)
        );
    }
    public static Stream<Arguments> provideParametersForSubtractionTests() {
        return Stream.of(
                Arguments.of(TestData.NUM1, TestData.NUM1, 0),
                Arguments.of(TestData.NUM2, TestData.NUM2, 0),
                Arguments.of(TestData.NUM3, TestData.NUM3, 0),

                Arguments.of(TestData.NUM1, TestData.NUM2, TestData.SUBTRACTION_RESULT12),
                Arguments.of(TestData.NUM2, TestData.NUM1, TestData.SUBTRACTION_RESULT21),

                Arguments.of(TestData.NUM1, TestData.NUM3, TestData.SUBTRACTION_RESULT13),
                Arguments.of(TestData.NUM3, TestData.NUM1, TestData.SUBTRACTION_RESULT31),


                Arguments.of(TestData.NUM2, TestData.NUM3, TestData.SUBTRACTION_RESULT23),
                Arguments.of(TestData.NUM3, TestData.NUM2, TestData.SUBTRACTION_RESULT32)

        );
    }
    public static Stream<Arguments> provideParametersForMultiplicationTests() {
        Random rng = new Random();
        int randomInt = rng.nextInt();

        return Stream.of(
                Arguments.of(rng.nextInt(), 0, 0),
                Arguments.of(randomInt, 1, randomInt),
                Arguments.of(TestData.NUM1, TestData.NUM1, TestData.MULTIPLICATION_RESULT11),
                Arguments.of(TestData.NUM2, TestData.NUM2, TestData.MULTIPLICATION_RESULT22),
                Arguments.of(TestData.NUM3, TestData.NUM3, TestData.MULTIPLICATION_RESULT33),

                Arguments.of(TestData.NUM2, TestData.NUM3, TestData.MULTIPLICATION_RESULT23),
                Arguments.of(TestData.NUM3, TestData.NUM2, TestData.MULTIPLICATION_RESULT23)
        );
    }

    @Test
    void shouldReturnGreetingMessage() {
        String actual = sut.getGreetingMessage();
        Assertions.assertEquals("Добро пожаловать в калькулятор", actual);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForAdditionTests")
    void shouldAddTwoIntegers(int num1, int num2, int expectedResult) {
        int actualResult = sut.add(num1, num2);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForSubtractionTests")
    void shouldSubtractTwoIntegers(int num1, int num2, int expectedResult) {
        int actualResult = sut.subtract(num1, num2);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForMultiplicationTests")
    void shouldMultiplyTwoIntegers(int num1, int num2, int expectedResult) {
        int actualResult = sut.multiply(num1, num2);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionOnDivisionByZeroAttempt() {
        Random rng = new Random();
        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.divide(rng.nextInt(), 0));
    }

    @Test
    void ShouldDivideTwoIntegers(){
        Assertions.assertEquals(TestData.DIVISION_RESULT23, sut.divide(TestData.NUM2, TestData.NUM3));
        Random rng = new Random();
        int num1 = rng.nextInt();
        int num2;
        do {
            num2 = rng.nextInt();
        } while (num2 == 0);

        if (Math.abs(num1) < Math.abs(num2)){
            Assertions.assertEquals(0, sut.divide(num1, num2));
        } else if (num1 == num2) {
            Assertions.assertEquals(1, sut.divide(num1, num2));
        } else {
            int expected = 0;
            for(int i = Math.abs(num1); i > Math.abs(num2); i -= Math.abs(num2)){
                expected++;
            }
            if (num1 < 0 ^ num2 < 0){
                expected *= -1;
            }
            Assertions.assertEquals(expected, sut.divide(num1, num2));
        }
    }
}

class TestData{
    public static int NUM1 = 1;
    public static int NUM2 = 25;
    public static int NUM3 = 8;
    public static int ADDITION_RESULT11 = 2;
    public static int ADDITION_RESULT12 = 26;
    public static int ADDITION_RESULT13 = 9;
    public static int ADDITION_RESULT22 = 50;
    public static int ADDITION_RESULT23 = 33;
    public static int ADDITION_RESULT33 = 16;

    public static int SUBTRACTION_RESULT12 = -24;
    public static int SUBTRACTION_RESULT13 = -7;
    public static int SUBTRACTION_RESULT21 = 24;
    public static int SUBTRACTION_RESULT23 = 17;
    public static int SUBTRACTION_RESULT31 = 7;
    public static int SUBTRACTION_RESULT32 = -17;

    public static int MULTIPLICATION_RESULT11 = 1;
    public static int MULTIPLICATION_RESULT22 = 625;
    public static int MULTIPLICATION_RESULT23 = 200;
    public static int MULTIPLICATION_RESULT33 = 64;

    public static int DIVISION_RESULT23 = 3;
}