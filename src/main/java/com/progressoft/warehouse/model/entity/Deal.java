package com.progressoft.warehouse.model.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table
public class Deal {
    @Id
    @GeneratedValue
    private int id;
    private int dealId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
    private String timeStamp;

}
