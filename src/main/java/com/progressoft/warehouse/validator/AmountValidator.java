package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidAmountException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class AmountValidator {

    public void validate(BigDecimal amount){
        if(Objects.isNull(amount)){
            throw new InvalidAmountException("Amount is required");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("The amount is less than or equal to zero");
        }

    }
}
