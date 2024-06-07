package com.progressoft.warehouse.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.warehouse.model.request.RequestDeal;
import com.progressoft.warehouse.service.DealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealController.class)
public class DealControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DealService dealService;


    @Test
    public void whenFindAll_thenExpectedResult() throws Exception {

        RequestDeal requestDeal = new RequestDeal();
        List<RequestDeal> allEmployees = List.of(requestDeal);
        when(dealService.findAll()).thenReturn(allEmployees);
        this.mvc.perform(get("/warehouse")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"dealId\":0,\"fromCurrency\":null,\"toCurrency\":null,\"timeStamp\":null,\"amount\":null}]")));
    }



    @Test
    public void givenRequestDeal_whenSave_thenExpectedResult() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        RequestDeal requestDeal = new RequestDeal();
        requestDeal.setDealId(32154);
        requestDeal.setTimeStamp(LocalDateTime.now().toString());
        requestDeal.setToCurrency("JOD");
        requestDeal.setFromCurrency("EUR");
        requestDeal.setAmount(BigDecimal.TEN);
        List<RequestDeal> allEmployees = List.of(requestDeal);
        when(dealService.findAll()).thenReturn(allEmployees);
        this.mvc.perform(post("/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDeal)))
                .andExpect(status().isOk());


    }
}