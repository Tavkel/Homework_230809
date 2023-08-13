package com.example.Homework_230809.services.implementations;

import com.example.Homework_230809.services.interfaces.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static com.example.Homework_230809.services.implementations.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {
    private final CalculatorService sut = new CalculatorServiceImpl();

    public static Stream<Arguments> provideParametersForAdditionTests() {
        return Stream.of(
                Arguments.of(NUM1, NUM1, ADDITION_RESULT11),

                Arguments.of(NUM1, NUM2, ADDITION_RESULT12),
                Arguments.of(NUM2, NUM1, ADDITION_RESULT12),

                Arguments.of(NUM1, NUM3, ADDITION_RESULT13),
                Arguments.of(NUM3, NUM1, ADDITION_RESULT13),

                Arguments.of(NUM2, NUM2, ADDITION_RESULT22),

                Arguments.of(NUM2, NUM3, ADDITION_RESULT23),
                Arguments.of(NUM3, NUM2, ADDITION_RESULT23),

                Arguments.of(NUM3, NUM3, ADDITION_RESULT33)
        );
    }

    public static Stream<Arguments> provideParametersForSubtractionTests() {
        return Stream.of(
                Arguments.of(NUM1, NUM1, 0),
                Arguments.of(NUM2, NUM2, 0),
                Arguments.of(NUM3, NUM3, 0),

                Arguments.of(NUM1, NUM2, SUBTRACTION_RESULT12),
                Arguments.of(NUM2, NUM1, SUBTRACTION_RESULT21),

                Arguments.of(NUM1, NUM3, SUBTRACTION_RESULT13),
                Arguments.of(NUM3, NUM1, SUBTRACTION_RESULT31),


                Arguments.of(NUM2, NUM3, SUBTRACTION_RESULT23),
                Arguments.of(NUM3, NUM2, SUBTRACTION_RESULT32)

        );
    }

    public static Stream<Arguments> provideParametersForMultiplicationTests() {
        Random rng = new Random();
        int randomInt = rng.nextInt();

        return Stream.of(
                Arguments.of(rng.nextInt(), 0, 0),
                Arguments.of(randomInt, 1, randomInt),
                Arguments.of(NUM1, NUM1, MULTIPLICATION_RESULT11),
                Arguments.of(NUM2, NUM2, MULTIPLICATION_RESULT22),
                Arguments.of(NUM3, NUM3, MULTIPLICATION_RESULT33),

                Arguments.of(NUM2, NUM3, MULTIPLICATION_RESULT23),
                Arguments.of(NUM3, NUM2, MULTIPLICATION_RESULT23)
        );
    }

    public static Stream<Arguments> provideParametersForDivisionTests() {
        return Stream.of(
                Arguments.of(NUM2, NUM3, DIVISION_RESULT23),
                Arguments.of(NUM3, NUM2, DIVISION_RESULT32)
        );
    }

    @Test
    void shouldReturnGreetingMessage() {
        String actual = sut.getGreetingMessage();
        assertEquals("Добро пожаловать в калькулятор", actual);
    }

    //region add
    @ParameterizedTest
    @MethodSource("provideParametersForAdditionTests")
    void add_shouldAddTwoIntegers(int num1, int num2, int expectedResult) {
        var actualResult = sut.add(num1, num2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void add_shouldAddTwosIntegers() {
        assertEquals(4_000_000L, sut.add(2_000_000, 2_000_000));
    }

    @Test
    void add_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> sut.add(null, null));
    }
//endregion

    //region subtract
    @ParameterizedTest
    @MethodSource("provideParametersForSubtractionTests")
    void subtract_shouldSubtractTwoIntegers(int num1, int num2, int expectedResult) {
        var actualResult = sut.subtract(num1, num2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void subtract_shouldSubtractTwosIntegers() {
        assertEquals(-4_000_000L, sut.subtract(-2_000_000, 2_000_000));
    }

    @Test
    void subtract_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> sut.subtract(null, null));
    }
    //endregion

    //region multiply
    @ParameterizedTest
    @MethodSource("provideParametersForMultiplicationTests")
    void multiply_shouldMultiplyTwoIntegers(int num1, int num2, int expectedResult) {
        var actualResult = sut.multiply(num1, num2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void multiply_shouldMultiplyTwosIntegers() {
        assertEquals(4_000_000_000_000L, sut.multiply(2_000_000, 2_000_000));
    }

    @Test
    void multiply_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> sut.multiply(null, null));
    }
    //endregion

    //region divide
    @ParameterizedTest
    @MethodSource("provideParametersForDivisionTests")
    void divide_ShouldDivideTwoIntegers(int num1, int num2, double expectedResult) {
        var actualResult = sut.divide(num1, num2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void divide_shouldDivideTwoIntegers() {
        assertEquals(-2, sut.divide(-8, 4));
    }

    @Test
    void divide_shouldThrowIllegalArgumentExceptionOnDivisionByZeroAttempt() {
        int aNumber = 2;
        assertThrows(IllegalArgumentException.class, () -> sut.divide(aNumber, 0));
    }
    //endregion
}

class TestData {
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

    public static double DIVISION_RESULT23 = 3.125;
    public static double DIVISION_RESULT32 = 0.32;

}