package com.example.stackoverflow.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

@DisplayNameGeneration(UtilsTest.ReplaceCamelCase.class)
class UtilsTest {
    @Test
    void convertStringToInteger_PositiveNumberString_ReturnNumber() {
        String numberString = "1";
        int expectedNumber = 1;
        String message = "The input";

        int result = Utils.convertStringToInteger(numberString, message);

        assertEquals(expectedNumber, result);
    }

    @Test
    void convertStringToInteger_NegativeNumberString_ReturnNumber() {
        String numberString = "-1";
        int expectedNumber = -1;
        String message = "The input";

        int result = Utils.convertStringToInteger(numberString, message);

        assertEquals(expectedNumber, result);
    }

    @Test
    void convertStringToInteger_ZeroNumberString_ReturnNumber() {
        String numberString = "0";
        int expectedNumber = 0;
        String message = "The input";

        int result = Utils.convertStringToInteger(numberString, message);

        assertEquals(expectedNumber, result);
    }

    @Test
    void convertStringToInteger_NumberAndSpacePrefixNumberString_ReturnNumber() {
        String numberString = " 1";
        int expectedNumber = 1;
        String message = "The input";

        int result = Utils.convertStringToInteger(numberString, message);

        assertEquals(expectedNumber, result);
    }

    @Test
    void convertStringToInteger_NumberAndSpaceSuffixNumberString_ReturnNumber() {
        String numberString = "1 ";
        int expectedNumber = 1;
        String message = "The input";

        int result = Utils.convertStringToInteger(numberString, message);

        assertEquals(expectedNumber, result);
    }

    @Test
    void convertStringToInteger_ALetterNumberString_ThrowException() {
        String numberString = "a";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_MultipleLetterNumberString_ThrowException() {
        String numberString = "ab";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_LetterAndNumberNumberString_ThrowException() {
        String numberString = "1a";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_SpecialCharacterNumberString_ThrowException() {
        String numberString = "!";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_EmptyNumberString_ThrowException() {
        String numberString = "";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_ASpaceNumberString_ThrowException() {
        String numberString = " ";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_MultipleSpacesNumberString_ThrowException() {
        String numberString = "  ";
        String message = "The input";
        String expectedMessage = "The input must be an integer number.";

        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void convertStringToInteger_NullNumberString_ThrowException() {
        String numberString = null;
        String message = "The input";
        String expectedMessage = "The input cannot be null.";

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> {
            Utils.convertStringToInteger(numberString, message);
        });
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    static class ReplaceCamelCase extends DisplayNameGenerator.Standard {
        public ReplaceCamelCase() {
        }

        public String generateDisplayNameForClass(Class<?> testClass) {
            return this.replaceCapitals(super.generateDisplayNameForClass(testClass));
        }

        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return this.replaceCapitals(super.generateDisplayNameForNestedClass(nestedClass));
        }

        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return this.replaceCapitals(testMethod.getName());
        }

        private String replaceCapitals(String name) {
            name = name.replaceAll("([A-Z])", " $1");
            return name;
        }
    }

}
