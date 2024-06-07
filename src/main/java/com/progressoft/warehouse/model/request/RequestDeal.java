package com.progressoft.warehouse.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestDeal {
    private int dealId;
    private String fromCurrency;
    private String toCurrency;
    private String timeStamp;
    private BigDecimal amount;
}
