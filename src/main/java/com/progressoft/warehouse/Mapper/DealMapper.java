package com.progressoft.warehouse.Mapper;

import com.progressoft.warehouse.model.entity.Deal;
import com.progressoft.warehouse.model.request.RequestDeal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DealMapper {

 Deal toDeal(RequestDeal requestDeal);


    RequestDeal toRequestPerson(Deal deal);
}
