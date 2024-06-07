package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class AmountValidatorTest {

    private final AmountValidator amountValidator = new AmountValidator();

    @Test
    public void givenNullAmount_whenValidate_thenThrowException() {
        InvalidAmountException exception = Assertions.assertThrows(InvalidAmountException.class,
                () -> amountValidator.validate(null));

        Assertions.assertEquals("Amount is required", exception.getMessage());

    }

    @Test
    public void givenInvalidAmount_whenValidate_thenThrowException() {
        InvalidAmountException exception = Assertions.assertThrows(InvalidAmountException.class,
                () -> amountValidator.validate(BigDecimal.ZERO));

        Assertions.assertEquals("The amount is less than or equal to zero", exception.getMessage());

    }

    @Test
    public void givenValidAmount_whenValidate_thenThrowException() {
        Assertions.assertDoesNotThrow(() -> amountValidator.validate(BigDecimal.TEN));

    }


}