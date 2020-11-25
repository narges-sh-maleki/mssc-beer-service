package com.maleki.narges.msscbeerservice.service;

import guru.sfg.brewery.common.BeerDto;
import guru.sfg.brewery.common.BeerPagedList;
import guru.sfg.brewery.common.BeerStyleEnum;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId,Boolean showInventory);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle,Boolean showInventory, Pageable pageInfo);

     BeerDto getBeerByUpc(String upc, Boolean showInventory);
}
