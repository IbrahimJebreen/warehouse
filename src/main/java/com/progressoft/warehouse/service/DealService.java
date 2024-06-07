package com.progressoft.warehouse.service;

import com.progressoft.warehouse.Mapper.DealMapper;
import com.progressoft.warehouse.model.entity.Deal;
import com.progressoft.warehouse.model.request.RequestDeal;
import com.progressoft.warehouse.repository.DealRepository;
import com.progressoft.warehouse.validator.AmountValidator;
import com.progressoft.warehouse.validator.DealIdValidator;
import com.progressoft.warehouse.validator.ISOCodeValidator;
import com.progressoft.warehouse.validator.TimeStampValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;
    private final ISOCodeValidator isoCodeValidator;
    private final AmountValidator amountValidator;
    private final DealIdValidator dealIdValidator;
    private final TimeStampValidator timeStampValidator;

    public DealService(DealRepository dealRepository, DealMapper dealMapper, ISOCodeValidator isoCodeValidator, AmountValidator amountValidator, DealIdValidator dealIdValidator, TimeStampValidator timeStampValidator) {
        this.dealRepository = dealRepository;
        this.dealMapper = dealMapper;
        this.isoCodeValidator = isoCodeValidator;
        this.amountValidator = amountValidator;
        this.dealIdValidator = dealIdValidator;
        this.timeStampValidator = timeStampValidator;
    }

    @Transactional(noRollbackFor = {RuntimeException.class})
    public void addDeal(RequestDeal requestDeal) {
        log.info("Starting add deal request ...");
        log.info("validating deal Id {}", requestDeal.getDealId());
        dealIdValidator.validate(requestDeal.getDealId());
        Deal deal = dealMapper.toDeal(requestDeal);
        log.info("trying to save deal on DB {}", requestDeal.getDealId());
        dealRepository.save(deal);
        log.info("deal is saved successfully on the DB {}", requestDeal.getDealId());
        log.info("validating ISO code from currency: {} to currency: {}", requestDeal.getFromCurrency(), requestDeal.getToCurrency());
        isoCodeValidator.validate(requestDeal.getFromCurrency(), requestDeal.getToCurrency());
        log.info("validating timestamp: {}", requestDeal.getTimeStamp());
        timeStampValidator.validate(String.valueOf(requestDeal.getTimeStamp()));
        log.info("validating amount {}", requestDeal.getAmount());
        amountValidator.validate(requestDeal.getAmount());
        log.info("Deal is added successfully");
    }

    public List<RequestDeal> findAll() {
        List<Deal> deals = dealRepository.findAll();
        log.info("Number of recurred fetched {}", deals.size());
        List<RequestDeal> requestPersonList = new ArrayList<>();
        for (Deal deal : deals) {
            requestPersonList.add(dealMapper.toRequestPerson(deal));
        }
        return requestPersonList;
    }
}
