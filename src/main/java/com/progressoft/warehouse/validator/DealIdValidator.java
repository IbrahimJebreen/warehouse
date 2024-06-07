package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidDealIdException;
import com.progressoft.warehouse.repository.DealRepository;
import org.springframework.stereotype.Component;

@Component
public class DealIdValidator {

    private final DealRepository dealRepository;

    public DealIdValidator(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }


    public void validate(int dealId) {
        if (dealId == 0) {
            throw new InvalidDealIdException("Id is required");
        }

        if (dealRepository.countByDealId(dealId) >= 1) {
            throw new InvalidDealIdException("Deal request already exists");
        }

    }
}
