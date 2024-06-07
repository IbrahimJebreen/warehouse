package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidTimeStampException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Component
public class TimeStampValidator {

    public void validate(String timeStamp) {

        if (Objects.isNull(timeStamp)) {
            throw new InvalidTimeStampException("Time stamp is required");
        }

        try {
            LocalDateTime.parse(timeStamp);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeStampException("Invalid format");
        }
    }
}
