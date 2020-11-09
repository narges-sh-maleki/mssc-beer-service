package com.maleki.narges.msscbeerservice.web.mapper;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.service.inventory.BeerInventoryService;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public abstract   class BeerMapperDecorator implements BeerMapper {

    @Autowired
    private  BeerMapper beerMapper;

    @Autowired
    private BeerInventoryService beerInventoryService;

    @Override
    public Beer beerDtoToBeer(BeerDto dto) {
        return beerMapper.beerDtoToBeer(dto);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        Integer quantity = beerInventoryService.getBeerInventory(beer.getId());
        BeerDto dto = beerMapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(quantity);

        return dto;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {

        return beerMapper.beerToBeerDto(beer);
    }


}
