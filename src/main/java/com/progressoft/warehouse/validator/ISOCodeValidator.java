package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidISOCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Objects;


@Slf4j
@Component
public class ISOCodeValidator {

    public void validate(String fromCurrency, String toCurrency) {

        if(Objects.isNull(fromCurrency)){
            throw new InvalidISOCodeException("From currency is required");
        }
        if(Objects.isNull(toCurrency)){
            throw new InvalidISOCodeException("To currency is required");
        }

        try {
            log.info("validating from currency");
            Currency.getInstance(fromCurrency);
            log.info("validating to currency");
            Currency.getInstance(toCurrency);
        } catch (IllegalArgumentException ex) {
            throw new InvalidISOCodeException("ISO Code is not valid");
        }

    }
}



