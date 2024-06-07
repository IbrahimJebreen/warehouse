package com.progressoft.warehouse.service;

import com.progressoft.warehouse.Mapper.DealMapper;
import com.progressoft.warehouse.model.entity.Deal;
import com.progressoft.warehouse.model.request.RequestDeal;
import com.progressoft.warehouse.repository.DealRepository;
import com.progressoft.warehouse.validator.AmountValidator;
import com.progressoft.warehouse.validator.DealIdValidator;
import com.progressoft.warehouse.validator.ISOCodeValidator;
import com.progressoft.warehouse.validator.TimeStampValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class DealServiceTest {

    private DealRepository dealRepository;
    private DealMapper dealMapper;
    private ISOCodeValidator isoCodeValidator;
    private AmountValidator amountValidator;
    private DealIdValidator dealIdValidator;
    private TimeStampValidator timeStampValidator;

    private DealService dealService;

    @BeforeEach
    void setUp() {
        dealRepository = Mockito.mock(DealRepository.class);
        dealMapper = Mockito.mock(DealMapper.class);
        isoCodeValidator = Mockito.mock(ISOCodeValidator.class);
        amountValidator = Mockito.mock(AmountValidator.class);
        dealIdValidator = Mockito.mock(DealIdValidator.class);
        timeStampValidator = Mockito.mock(TimeStampValidator.class);
        dealService = new DealService(dealRepository, dealMapper, isoCodeValidator, amountValidator, dealIdValidator, timeStampValidator);
    }

    @Test
    public void givenValidDealRequest_whenAddDeal_thenExpectedResult() {

        RequestDeal requestDeal = new RequestDeal();
        requestDeal.setDealId(10);
        requestDeal.setAmount(BigDecimal.TEN);
        requestDeal.setFromCurrency("JOD");
        requestDeal.setToCurrency("EUR");
        requestDeal.setTimeStamp(LocalDateTime.now().toString());

        Deal dealEntity = new Deal();
        dealEntity.setDealId(10);
        dealEntity.setAmount(BigDecimal.TEN);
        dealEntity.setFromCurrency("JOD");
        dealEntity.setToCurrency("EUR");
        dealEntity.setTimeStamp(LocalDateTime.now().toString());


        Mockito.when(dealMapper.toDeal(requestDeal)).thenReturn(dealEntity);

        dealService.addDeal(requestDeal);

        Mockito.verify(dealRepository).save(dealEntity);
        Mockito.verify(amountValidator).validate(requestDeal.getAmount());
        Mockito.verify(dealIdValidator).validate(requestDeal.getDealId());
        Mockito.verify(timeStampValidator).validate(requestDeal.getTimeStamp());
        Mockito.verify(isoCodeValidator).validate(requestDeal.getFromCurrency(), requestDeal.getToCurrency());
    }

}