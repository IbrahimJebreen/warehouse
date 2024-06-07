package com.progressoft.warehouse.validator;


import com.progressoft.warehouse.exception.InvalidTimeStampException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeStampValidatorTest {

    private final TimeStampValidator timeStampValidator = new TimeStampValidator();

    @Test
    public void givenInvalidTimeStamp_whenValidate_thenThrowException() {

        InvalidTimeStampException exception = Assertions.assertThrows(InvalidTimeStampException.class,
                () -> timeStampValidator.validate("blala"));

        Assertions.assertEquals("Invalid format", exception.getMessage());
    }

    @Test
    public void givenNullTimeStamp_whenValidate_thenThrowException() {
        InvalidTimeStampException invalidTimeStampException = Assertions.assertThrows(InvalidTimeStampException.class,
                () -> timeStampValidator.validate(null));

        Assertions.assertEquals("Time stamp is required", invalidTimeStampException.getMessage());
    }

    @Test
    public void givenValidTimestamp_whenValidate_thenExpectedResult() {
        Assertions.assertDoesNotThrow(() -> timeStampValidator.validate("2024-06-06T23:35:14.2367498"));
    }


}