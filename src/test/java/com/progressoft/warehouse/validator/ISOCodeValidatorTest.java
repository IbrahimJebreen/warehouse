package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidISOCodeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ISOCodeValidatorTest {

    private final ISOCodeValidator isoCodeValidator = new ISOCodeValidator();

    @Test
    public void givenInvalidISOCode_whenValidate_thenThrowException() {
        InvalidISOCodeException exception = Assertions.assertThrows(InvalidISOCodeException.class,
                () -> isoCodeValidator.validate("jod", "aoa"));
        Assertions.assertEquals("ISO Code is not valid", exception.getMessage());

    }

    @Test
    public void givenNullFromCurrencyISOCode_whenValidate_thenThrowException() {
        InvalidISOCodeException exception = Assertions.assertThrows(InvalidISOCodeException.class,
                () -> isoCodeValidator.validate(null, "AOA"));
        Assertions.assertEquals("From currency is required", exception.getMessage());

    }

    @Test
    public void givenNullToCurrencyISOCode_whenValidate_thenThrowException() {
        InvalidISOCodeException exception = Assertions.assertThrows(InvalidISOCodeException.class,
                () -> isoCodeValidator.validate("JOD", null));
        Assertions.assertEquals("To currency is required", exception.getMessage());

    }

    @Test
    public void givenInvalidFromCurrencyAndValidToCurrency_whenValidate_thenThrowException() {
        InvalidISOCodeException exception = Assertions.assertThrows(InvalidISOCodeException.class,
                () -> isoCodeValidator.validate("aoa", "JOD"));
        Assertions.assertEquals("ISO Code is not valid", exception.getMessage());
    }

    @Test
    public void givenInvalidToCurrencyAndValidFromCurrency_whenValidate_thenThrowException() {
        InvalidISOCodeException exception = Assertions.assertThrows(InvalidISOCodeException.class,
                () -> isoCodeValidator.validate("JOD", "aoa"));
        Assertions.assertEquals("ISO Code is not valid", exception.getMessage());
    }

    @Test
    public void givenValidToCurrencyAndValidFromCurrency_whenValidate_thenExpectedResult() {
        Assertions.assertDoesNotThrow(() -> isoCodeValidator.validate("JOD", "AOA"));
    }

}