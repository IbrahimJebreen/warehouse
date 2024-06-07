package com.progressoft.warehouse.controller;

import com.progressoft.warehouse.model.request.RequestDeal;
import com.progressoft.warehouse.service.DealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/warehouse")
public class DealController {


    private final DealService dealService;


    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    public void save(@RequestBody RequestDeal requestDeal){

        dealService.addDeal(requestDeal);
    }

    @GetMapping
    public ResponseEntity<List<RequestDeal>> findAll() {
        return ResponseEntity.ok(dealService.findAll());
    }

}
