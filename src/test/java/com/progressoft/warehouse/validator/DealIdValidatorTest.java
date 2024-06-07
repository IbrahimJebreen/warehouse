package com.progressoft.warehouse.validator;

import com.progressoft.warehouse.exception.InvalidDealIdException;
import com.progressoft.warehouse.repository.DealRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;

class DealIdValidatorTest {

    private DealRepository dealRepository;

    private DealIdValidator dealIdValidator;


    @BeforeEach
    void setUp() {
        dealRepository = Mockito.mock(DealRepository.class);
        dealIdValidator = new DealIdValidator(dealRepository);
    }

    @Test
    public void givenValidIdAndNotExist_whenValidate_thenExpectedResult() {
        Mockito.when(dealRepository.countByDealId(eq(11))).thenReturn(1L);
        Assertions.assertDoesNotThrow(() -> dealIdValidator.validate(11));
    }

    @Test
    public void givenExitsId_whenValidate_thenExpectedResult() {
        Mockito.when(dealRepository.countByDealId(eq(10))).thenReturn(2L);
        InvalidDealIdException exception = Assertions.assertThrows(InvalidDealIdException.class,
                () -> dealIdValidator.validate(10));

        Mockito.verify(dealRepository).countByDealId(eq(10));
        Assertions.assertEquals("Deal request already exists", exception.getMessage());
    }

    @Test
    public void givenNullId_whenValidate_thenExpectedResult() {
        InvalidDealIdException exception = Assertions.assertThrows(InvalidDealIdException.class,
                () -> dealIdValidator.validate(0));

        Mockito.verifyNoInteractions(dealRepository);
        Assertions.assertEquals("Id is required", exception.getMessage());
    }

}